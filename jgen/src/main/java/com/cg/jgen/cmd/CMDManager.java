package com.cg.jgen.cmd;

import com.cg.jgen.cmd.evaluators.ArgumentEvaluator;
import com.cg.jgen.cmd.exception.CommandLineException;
import com.cgm.storm.utils.common.StringUtils;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class CMDManager {
    private String command;
    private List<Argument> argsList;
    private List<String> atLeastOneList;
    private List<List<String>> allOrNoneOfList;

    public CMDManager(String command) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(command), "command name is empty or Null");

        this.command = command;
        this.argsList = new ArrayList<>();
        this.atLeastOneList = new ArrayList<>();
        this.allOrNoneOfList = new ArrayList<>();
    }

    public void defineArgument(String name, String description, boolean mandatory) {
        argsList.add(new Argument(name, description, mandatory));
    }

    public void defineArgument(String name, String description, boolean mandatory, ArgumentEvaluator evaluator) {
        argsList.add(new Argument(name, description, mandatory, evaluator));
    }

    public void atLeastOneOf(String... atLeastOneArgs) {
        atLeastOneList.clear();
        for (String argName : atLeastOneArgs) {
            Argument arg = getArgument(argName);
            if (arg == null) {
                throw new CommandLineException("Argument '" + argName + "' not match with the definition. Please type the list as the arguments are defined");
            }

            atLeastOneList.add(argName);
        }
    }

    public void allOrNoneOf(String... allOrNonArgs) {
        List<String> list = new ArrayList<>();
        for (String argName : allOrNonArgs) {
            Argument arg = getArgument(argName);
            if (arg == null) {
                throw new CommandLineException("Argument '" + argName + "' not match with the definition. Please type the list as the arguments are defined");
            }

            list.add(argName);
        }

        if (list.size() > 0) {
            allOrNoneOfList.add(list);
        }
    }

    public boolean isPresent(String name) {
        Argument arg = getArgument(name);

        return arg != null && arg.isPresent();
    }

    private Argument getArgument(String name) {
        for (Argument arg : argsList) {
            if (arg.getName().equals(name)) {
                return arg;
            }
        }

        return null;
    }

    public String getArgumentValue(String name) {
        Argument arg = getArgument(name);

        return arg != null ? arg.getValue() : "";
    }

    public void parse(String[] argsList) {
        int i = 0;
        int n = argsList.length;

        while (i < n) {
            String token = argsList[i];
            Argument arg = getArgument(token);
            if (arg == null) {
                throw new CommandLineException("Unknown token: '" + token + "'");
            }

            arg.setPresent(true);
            if (arg.haveParameter()) {
                i++;
                if (i < n) {
                    String parameter = argsList[i];
                    if (arg.evaluate(parameter)) {
                        arg.setValue(parameter);
                    } else {
                        throw new CommandLineException("Invalid parameter '" + parameter + "' in argument: " + token);
                    }
                } else {
                    throw new CommandLineException("Parameter missing for argument: " + token);
                }
            }
            i++;
        }

        evaluatePresence();
    }

    private void evaluatePresence() {
        // mandatory arguments
        for (Argument arg : argsList) {
            if (arg.isMandatory() && !arg.isPresent()) {
                throw new CommandLineException("Argument missing: " + arg.getName());
            }
        }

        // "at least one ..." must be present
        boolean atLeastOne = false;
        for (String argName : atLeastOneList) {
            if (isPresent(argName)) {
                atLeastOne = true;
                break;
            }
        }
        if (!atLeastOne && atLeastOneList.size() > 0) {
            throw new CommandLineException("Arguments missing. You must write at least one: " + atLeastOneList);
        }

        // "all or none of ..." must be present
        int correct = 0;

        StringBuilder incorrectCombination = new StringBuilder();
        for (List<String> list : allOrNoneOfList) {
            int count = 0;
            for (String argName : list) {
                if (isPresent(argName)) {
                    count++;
                }
            }
            if (count == 0 || count == list.size()) {
                correct++;
            } else {
                incorrectCombination.append("\n");
                incorrectCombination.append(list);
            }
        }

        if (correct == 0) {
            throw new CommandLineException("Arguments missing. You must choose at least one of these combinations: " + incorrectCombination.toString());
        }
    }

    public String usage() {
        int n = 1;
        StringBuilder sbWhere = new StringBuilder("\nwhere:");
        StringBuilder sbUsage = new StringBuilder("usage:\n    " + command);
        for (Argument arg : argsList) {
            String par = "";
            if (arg.haveParameter()) {
                par = " <par" + n + ">";
            }

            String argument = StringUtils.stringFormat(0, " {}{}", arg.getName(), par);
            sbUsage.append(argument);

            String argumentAndDescription = StringUtils.stringFormat(25, "\n   {}", argument);

            sbWhere.append(argumentAndDescription);
            sbWhere.append(arg.getDescription());
            n++;
        }

        return sbUsage.toString() + sbWhere.toString();
    }
}
