package com.cg.jgen.generator;

import com.cg.jgen.DataTestProvider;
import com.cg.jgen.core.Config;
import com.cg.jgen.core.Entity;
import com.cg.jgen.core.FileTemplate;
import com.cg.jgen.core.generator.Generator;
import com.cg.jgen.core.generator.TsGenerator;

/**
 *
 */
public class TSGeneratorTest extends BaseGeneratorTest {

    @Override
    public void injectParameters() {
        // given
        Config config = new Config();
        config.setAppPath("../jgen");
        config.setRootPackage("com.cg.jgen");
        config.setJarPath("jgen-test.jar");

        Generator gen = new TsGenerator(config);
        FileTemplate ft = DataTestProvider.getFileTemplate("TsGeneratorTemplate");
        Entity ett = DataTestProvider.getEntityPersonTest();

        String expected = "PersonTest" + "\n" +
                "    id: string;" + "\n" +
                "    firstName: string;" + "\n" +
                "    lastName: string;" + "\n" +
                "    sys_version: number;" + "\n" +
                "" + "\n" +
                "// EntityIds" + "\n" +
                "    id: string ''" + "\n" +
                "" + "\n" +
                "// EntityNoIds" + "\n" +
                "    firstName: string = ''" + "\n" +
                "    lastName: string = ''" + "\n" +
                "    sys_version: number = 0" + "\n" +
                "" + "\n" +
                "PersonTestService" + "\n" +
                "    delete(id: string): IResult< void >" + "\n" +
                "    filterCount(filter: IFilter): IResult< number >" + "\n" +
                "    find(range: IRange, sort: ISort, filter: IFilter): IResult< Array< IPersonTest > >" + "\n" +
                "    findById(id: string): IResult< IPersonTest >" + "\n" +
                "    getAll(): IResult< Array< IPersonTest > >" + "\n" +
                "    getAllCount(): IResult< number >" + "\n" +
                "    getById(id: string): IResult< IPersonTest >" + "\n" +
                "    newMethod(): IResult< void >" + "\n" +
                "    save(personTest: IPersonTest): IResult< IPersonTest >" + "\n" +
                "";

        setParameters(gen, ft, ett, expected);
    }
}
