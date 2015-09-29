package com.cg.jgen;

import com.cg.jgen.cmd.CMDManager;
import com.cg.jgen.cmd.evaluators.FileEvaluator;
import com.cg.jgen.cmd.exception.CommandLineException;
import com.cg.jgen.core.Config;
import com.cg.jgen.core.JGenEnvironment;
import com.cg.jgen.core.generator.GeneratorType;
import com.cg.jgen.core.importer.EnvironmentLoader;
import com.cg.jgen.utils.FileAssistant;
import com.cg.jgen.utils.Globals;
import com.cgm.storm.tools.migration.MigrationFileUpdater;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 *
 */
public class Main {
    private static final String JGEN_DIR = Globals.fixBackslashWithSlash(System.getProperty("user.dir")) + "/";
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("Run directory: {}", JGEN_DIR);
        CMDManager cmd = createCMDManager();

        try {
            //String list[] = {"-conf", "config-test.xml", "-java", "-pdex", "database2.xml"};
            //String list[] = {"-conf", "config.xml", "-java", "-pdex", "c:/temp/storm.xml"};
            //String list[] = {"-pdex", "storm.xml", "-conf", "config.xml", "-compile", "-mig-update"};
            //String list[] = {"-pdex", "storm.xml", "-conf", "config.xml", "-backend"};
            //cmd.parse(list);
            cmd.parse(args);

            if (cmd.isPresent("-h")) {
                System.out.println("\n" + cmd.usage());
                return;
            }

            if (cmd.isPresent("-readme")) {
                String data = FileAssistant.readFileAsString("README");
                System.out.println(data);
                return;
            }

            String configFile = cmd.getArgumentValue("-conf");
            String database = cmd.getArgumentValue("-pdex");

            JGenEnvironment env = EnvironmentLoader.load(configFile, database);
            Config config = env.getConfig();
            String appPath = config.getAppPath();

            System.out.println("=======================================");
            System.out.println("Configuration files are correct!");
            System.out.println("=======================================");
            if (cmd.isPresent("-check")) {
                return;
            }

            JGenManager jgen = new JGenManager();
            if (cmd.isPresent("-backend")) {
                jgen.render(env, Arrays.asList(GeneratorType.JAVA, GeneratorType.JAVA_ENUM, GeneratorType.XML));
            } else {
                if (cmd.isPresent("-java")) {
                    jgen.render(env, GeneratorType.JAVA);
                }
                if (cmd.isPresent("-jenum")) {
                    jgen.render(env, GeneratorType.JAVA_ENUM);
                }
                if (cmd.isPresent("-xml")) {
                    jgen.render(env, GeneratorType.XML);
                }
            }

            if (cmd.isPresent("-compile")) {
                System.out.println("=======================================");
                System.out.println("Compiling Java files");
                System.out.println("=======================================");
                Globals.compile(appPath, env.getModules());
                System.out.println("\n\n=======================================");
                System.out.println("Compilation successful!");
                System.out.println("=======================================\n\n");
            }

            if (cmd.isPresent("-mig-update") && config.isValidToUpdateMigrationFile()) {
                new MigrationFileUpdater(appPath,
                        config.getDatabaseProject(),
                        config.getDatabaseUpgradeTest(),
                        Globals.concatDirectoryNames(appPath, config.getMigrationFile()));
            }

            if (cmd.isPresent("-ts") || cmd.isPresent("-frontend")) {
                jgen.render(env, GeneratorType.TYPE_SCRIPT);
            }
            jgen.logFilesCreated();
            System.out.println("\nExecution successful!");
        } catch (CommandLineException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("\n" + cmd.usage());

        } catch (Exception e) {
            log.error("", e);
            System.out.println("\nExecution failed!");
        }
    }

    public static CMDManager createCMDManager() {
        CMDManager manager = new CMDManager("jgen");
        FileEvaluator xmlEvaluator = new FileEvaluator("xml");

        manager.defineArgument("-pdex", "define the pdex file", false, xmlEvaluator);
        manager.defineArgument("-conf", "define the xml config file", false, xmlEvaluator);
        // ----------
        manager.defineArgument("-java", "generate java files", false);
        manager.defineArgument("-jenum", "generate java enumeration files", false);
        manager.defineArgument("-ts", "generate TypeScript files", false);
        manager.defineArgument("-xml", "generate xml files", false);

        manager.defineArgument("-backend", "generate backend-code (java entities, services, enums... and xml resources)", false);
        manager.defineArgument("-frontend", "generate frontend-code (typescript entities and services)", false);

        manager.defineArgument("-mig-update", "update migration file", false);
        manager.defineArgument("-compile", "compile Java files", false);
        manager.defineArgument("-check", "check if the configuration files are correct", false);
        manager.defineArgument("-readme", "show README file", false);
        manager.defineArgument("-h", "this help", false);
        // ----------
        manager.atLeastOneOf("-pdex", "-conf", "-backend", "-frontend", "-java", "-jenum", "-xml", "-ts", "-check", "-compile", "-mig-update", "-readme", "-h");

        manager.allOrNoneOf("-pdex", "-conf", "-check");
        manager.allOrNoneOf("-pdex", "-conf", "-compile");
        manager.allOrNoneOf("-pdex", "-conf", "-mig-update");

        manager.allOrNoneOf("-pdex", "-conf", "-java");
        manager.allOrNoneOf("-pdex", "-conf", "-jenum");
        manager.allOrNoneOf("-pdex", "-conf", "-ts");
        manager.allOrNoneOf("-pdex", "-conf", "-xml");
        manager.allOrNoneOf("-pdex", "-conf", "-backend");
        manager.allOrNoneOf("-pdex", "-conf", "-frontend");

        return manager;
    }

    /*public static List<GeneratorType> getGeneratorsList(CMDManager cmd) {
        List<GeneratorType> list = new ArrayList<>();

        if (cmd.isPresent("-java")) {
            Globals.addIfNotListed(list, GeneratorType.JAVA);
            Globals.addIfNotListed(list, GeneratorType.JAVA_ENUM);
            Globals.addIfNotListed(list, GeneratorType.XML);
        }
        if (cmd.isPresent("-jenum")){
            Globals.addIfNotListed(list, GeneratorType.JAVA_ENUM);
        }
        if (cmd.isPresent("-ts")) {
            Globals.addIfNotListed(list, GeneratorType.TYPE_SCRIPT);
        }
        if (cmd.isPresent("-xml")) {
            Globals.addIfNotListed(list, GeneratorType.XML);
        }

        return list;
    }*/
}
