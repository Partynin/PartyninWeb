package labratorywork3;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

/**
 * Задание 2
 * Разработать на Java консольное приложение, имеющее два входных параметра: имена входного и выходного файла.
 * Задача приложения заключается в проверке значения средней оценки и его коррекции, если в исходном документе
 * оно не соответствует действительности. Использовать DOM - анализатор.
 */

public class ParserXML {

    public static void main(String[] args) throws IOException {
        // Check command-line parameter usage
        if (args.length != 2) {
            System.out.println("В качестве аргументов должны использоваться имена входного и выходного файла.");
            System.exit(1);
        }

        // Check if source file exists
        File sourceFile = new File(args[0]);
        if (!sourceFile.exists()) {
            System.out.println("Source file " + args[0] + " does not exist");
            System.exit(2);
        }

        // Check if target file exists
        File targetFile = new File(args[1]);
        if (targetFile.exists()) {
            System.out.println("Target file " + args[1] + " already exists");
            System.exit(3);
        }

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documentStudent = builder.parse("student.xml");
            System.out.println("The attributes of each element and average value are: ");
            printElementAttributesAndAverageValue(documentStudent);
            System.out.println();

            if (!checkAverageValue(documentStudent)) {
                createStudent2XMLFile(documentStudent);
            }
        } catch (Exception e) {
            System.out.println("Error in main method");
            System.out.println(e.toString());
        }
    }

    static void createStudent2XMLFile(Document documentStudent) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("student.xml");
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, doc.getDoctype().getSystemId());
            FileOutputStream f = new FileOutputStream("student2.xml");
            t.transform(new DOMSource(doc), new StreamResult(f));

            NodeList nl = doc.getElementsByTagName("*");
            for (int j = 0; j < nl.getLength(); j++) {
                Element e = (Element) nl.item(j);
                if (e.getTagName().equals("average")) {
                    System.out.println(" Average value in file student2 = " + e.getTextContent());
                    e.setTextContent(getAverageValue(documentStudent));
                }
            }

            // Записываем изменения в XML файл student2.xml    
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("student2.xml"));
            transformer.transform(source, result);
        } catch (Exception e) {
            System.out.println("Error in create student method");
            e.printStackTrace();
        }
    }

    static String getAverageValue(Document documentStudent) {
        double averageActualTotal = 0.0;
        int countMarks = 0;
        NodeList nl = documentStudent.getElementsByTagName("*");
        for (int j = 0; j < nl.getLength(); j++) {
            Element e = (Element) nl.item(j);
            NamedNodeMap nnm = e.getAttributes();
            if (nnm != null) {
                for (int i = 0; i < nnm.getLength(); i++) {
                    Node n = nnm.item(i);
                    if (n.getNodeName().equals("mark")) {
                        averageActualTotal = averageActualTotal + Double.parseDouble(n.getNodeValue());
                        countMarks++;
                    }
                }
            }
        }

        return String.valueOf(averageActualTotal / countMarks);
    }

    static boolean checkAverageValue(Document doc) {
        double averageActualTotal = 0.0;
        double averageInFile = 0.0;
        int countMarks = 0;
        NodeList nl = doc.getElementsByTagName("*");
        for (int j = 0; j < nl.getLength(); j++) {
            Element e = (Element) nl.item(j);
            NamedNodeMap nnm = e.getAttributes();
            if (nnm != null) {
                for (int i = 0; i < nnm.getLength(); i++) {
                    Node n = nnm.item(i);
                    if (n.getNodeName().equals("mark")) {
                        averageActualTotal = averageActualTotal + Double.parseDouble(n.getNodeValue());
                        countMarks++;
                    }
                }
            }
            if (e.getTagName().equals("average")) {
                System.out.println(" Average value in file = " + e.getTextContent());
                averageInFile = Double.parseDouble(e.getTextContent());
            }
        }

        System.out.println(" Actual average value = " + averageActualTotal / countMarks);

        return (averageActualTotal / countMarks) == averageInFile;
    }

    static void printElementAttributesAndAverageValue(Document doc) {
        NodeList nl = doc.getElementsByTagName("*");
        for (int j = 0; j < nl.getLength(); j++) {
            Element e = (Element) nl.item(j);
            System.out.println(e.getTagName() + ":");
            NamedNodeMap nnm = e.getAttributes();
            if (nnm != null) {
                for (int i = 0; i < nnm.getLength(); i++) {
                    Node n = nnm.item(i);
                    System.out.print(" " + n.getNodeName() + " = " + n.getNodeValue());
                }
            }
            if (e.getTagName().equals("average")) {
                System.out.print(" average value = " + e.getTextContent());
            }
            System.out.println();
        }
    }

}
