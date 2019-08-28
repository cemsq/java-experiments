package com.jgen;

import com.jgen.model.pdex.Database;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.*;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws JAXBException, IOException, SAXException {

        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
//        Schema schema = sf.newSchema(new File("jgen2\\schema\\pdexSchema.xml"));

        JAXBContext jc = JAXBContext.newInstance(Database.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
//        unmarshaller.setSchema(schema);
        Database database = (Database) unmarshaller.unmarshal(new File("jgen2\\storm.xml"));


//        List<Table> tables = database.getTables().getTable();
//        Table table = tables.get(0);

//        table.getColumns().getColumn().get(0).getType()
//
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(database, System.out);


    }


}
