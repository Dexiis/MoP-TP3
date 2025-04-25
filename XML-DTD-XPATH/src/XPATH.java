import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import java.io.File;
import java.io.IOException;

public class XPATH {
    private void lookingForEmployeList(String entity, String ficheiro) {
        Document document;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(new File("XML/" + ficheiro + ".xml"));

            XPathFactory xpathFactory = XPathFactory.newInstance();
            XPath xpath = xpathFactory.newXPath();
            String expression = getExpression(entity);

            NodeList tratadorNodes = (NodeList) xpath.evaluate(expression, document, XPathConstants.NODESET);

            if (tratadorNodes.getLength() == 0) {
                System.out.println("Nenhum tratador encontrado no XML.");
            } else {
                for (int i = 0; i < tratadorNodes.getLength(); i++) {
                    Node node = tratadorNodes.item(i);
                    Element tratadorElement = (Element) node;

                    String idTratador = tratadorElement.getAttribute("ID");
                    String nameTratador = tratadorElement.getElementsByTagName("name").item(0).getTextContent();
                    String ageTratador = tratadorElement.getElementsByTagName("age").item(0).getTextContent();
                    String experienceTratador = tratadorElement.getElementsByTagName("experience").item(0).getTextContent();

                    System.out.println("ID: " + idTratador + ", Nome: " + nameTratador + ", Idade: " + ageTratador + ", Experiência: " + experienceTratador);

                    NodeList associatedAnimals = tratadorElement.getElementsByTagName("animal_associated");
                    if (associatedAnimals.getLength() > 0) {
                        System.out.println("  Animais Associados:");
                        for (int j = 0; j < associatedAnimals.getLength(); j++) {
                            System.out.println("    - " + tratadorElement.getElementsByTagName("experience").item(j).getTextContent());
                        }
                    }
                }
            }
        } catch (ParserConfigurationException | XPathExpressionException | SAXException | IOException e) {
            System.err.println("Erro ao tentar obter os dados do XML.");
        }
    }

    private static String getExpression(String entity) {
        String expression;
        switch (entity) {
            case "leao" -> expression = "/zoo/animais/leoes/leao";
            case "elefante" -> expression = "/zoo/animais/elefantes/elefante";
            case "girafa" -> expression = "/zoo/animais/girafas/girafa";
            case "pinguim" -> expression = "/zoo/animais/pinguins/pinguim";
            case "tratador" -> expression = "/zoo/funcionarios/tratadores/tratador";
            case "administrador" -> expression = "/zoo/funcionarios/administradores/administrador";
            case "guia" -> expression = "/zoo/funcionarios/guias/guia";
            default -> expression = null;
        }
        return expression;
    }

    private void lookingForAnimalCharacteristic(String attribute, String attributeRequired, String ficheiro) {
        Document document;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(new File("XML/" + ficheiro + ".xml"));

            XPathFactory xpathFactory = XPathFactory.newInstance();
            XPath xpath = xpathFactory.newXPath();
            String[] animals = {"leos/leao", "elefantes/elefante", "girafas/girafa", "pinguins/pinguim"};


            for (String expression : animals) {
                NodeList animalsNodes = (NodeList) xpath.evaluate("/zoo/animais/" + expression, document, XPathConstants.NODESET);

                if (animalsNodes.getLength() != 0) {
                    for (int i = 0; i < animalsNodes.getLength(); i++) {
                        Node node = animalsNodes.item(i);
                        Element animalElement = (Element) node;
                        if (animalElement.getElementsByTagName(attribute).item(0).getTextContent().equals(attributeRequired)) {
                            String nameAnimal = animalElement.getAttribute("name");
                            String ageAnimal = animalElement.getElementsByTagName("age").item(0).getTextContent();
                            String weightAnimal = animalElement.getElementsByTagName("weight").item(0).getTextContent();
                            String dietAnimal = animalElement.getElementsByTagName("diet").item(0).getTextContent();
                            String typeAnimal = animalElement.getElementsByTagName("type").item(0).getTextContent();

                            System.out.println("Nome: " + nameAnimal + ", Idade: " + ageAnimal + ", Peso: " + weightAnimal + ", Dieta: " + dietAnimal + ", Tipo: " + typeAnimal);
                        }
                    }
                }
            }
        } catch (ParserConfigurationException | XPathExpressionException | SAXException | IOException e) {
            System.err.println("Erro ao tentar obter os dados do XML.");
        }
    }

    private void lookingForEmployesCharacteristic(String attribute, String attributeRequired, String ficheiro) {
        Document document;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(new File("XML/" + ficheiro + ".xml"));

            XPathFactory xpathFactory = XPathFactory.newInstance();
            XPath xpath = xpathFactory.newXPath();
            String[] employes = {"tratadores/tratador", "administradores/administrador", "guias/guia"};


            for (String expression : employes) {
                NodeList employesNodes = (NodeList) xpath.evaluate("/zoo/animais/" + expression, document, XPathConstants.NODESET);

                if (employesNodes.getLength() != 0) {
                    for (int i = 0; i < employesNodes.getLength(); i++) {
                        Node node = employesNodes.item(i);
                        Element employeElement = (Element) node;
                        if (employeElement.getElementsByTagName(attribute).item(0).getTextContent().equals(attributeRequired)) {
                            String nameEmploye = employeElement.getElementsByTagName("name").item(0).getTextContent();
                            String ageEmploye = employeElement.getElementsByTagName("age").item(0).getTextContent();
                            String idEmploye = employeElement.getAttribute("ID");
                            String experienceEmploye = employeElement.getElementsByTagName("experience").item(0).getTextContent();

                            System.out.println("Nome: " + nameEmploye + ", Idade: " + ageEmploye + ", ID: " + idEmploye + ", Experience: " + experienceEmploye);

                            NodeList associatedAnimals = employeElement.getElementsByTagName("animal_associated");
                            if (associatedAnimals.getLength() > 0) {
                                System.out.println("  Animais Associados:");
                                for (int j = 0; j < associatedAnimals.getLength(); j++) {
                                    System.out.println("    - " + employeElement.getElementsByTagName("experience").item(j).getTextContent());
                                }
                            }
                        }
                    }
                }
            }
        } catch (ParserConfigurationException | XPathExpressionException | SAXException | IOException e) {
            System.err.println("Erro ao tentar obter os dados do XML.");
        }
    }

    private void lookingForVisitantesCharacteristic(String attribute, String attributeRequired, String ficheiro) {
        Document document;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(new File("XML/" + ficheiro + ".xml"));

            XPathFactory xpathFactory = XPathFactory.newInstance();
            XPath xpath = xpathFactory.newXPath();
            NodeList visitantesNodes = (NodeList) xpath.evaluate("/zoo/visitantes/visitante", document, XPathConstants.NODESET);

            if (visitantesNodes.getLength() != 0) {
                for (int i = 0; i < visitantesNodes.getLength(); i++) {
                    Node node = visitantesNodes.item(i);
                    Element visitanteElement = (Element) node;
                    if (visitanteElement.getElementsByTagName(attribute).item(0).getTextContent().equals(attributeRequired)) {
                        String nameVisitante = visitanteElement.getElementsByTagName("name").item(0).getTextContent();
                        String ageVisitante = visitanteElement.getElementsByTagName("age").item(0).getTextContent();
                        String idVisitante = visitanteElement.getAttribute("ID");

                        System.out.println("Nome: " + nameVisitante + ", Idade: " + ageVisitante + ", ID: " + idVisitante);
                    }
                }
            }

        } catch (ParserConfigurationException | XPathExpressionException | SAXException | IOException e) {
            System.err.println("Erro ao tentar obter os dados do XML.");
        }
    }

    private void lookingForNumber(String entity, String ficheiro) {
        Document document;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(new File("XML/" + ficheiro + ".xml"));

            XPathFactory xpathFactory = XPathFactory.newInstance();
            XPath xpath = xpathFactory.newXPath();
            String expression = getExpression(entity);

            NodeList tratadorNodes = (NodeList) xpath.evaluate(expression, document, XPathConstants.NODESET);

            if (tratadorNodes.getLength() == 0) {
                System.out.println("Nenhum tratador encontrado no XML.");
            } else {
                for (int i = 0; i < tratadorNodes.getLength(); i++) {
                    Node node = tratadorNodes.item(i);
                    Element tratadorElement = (Element) node;

                    String idTratador = tratadorElement.getAttribute("ID");
                    String nameTratador = tratadorElement.getElementsByTagName("name").item(0).getTextContent();
                    String ageTratador = tratadorElement.getElementsByTagName("age").item(0).getTextContent();
                    String experienceTratador = tratadorElement.getElementsByTagName("experience").item(0).getTextContent();

                    System.out.println("ID: " + idTratador + ", Nome: " + nameTratador + ", Idade: " + ageTratador + ", Experiência: " + experienceTratador);

                    NodeList associatedAnimals = tratadorElement.getElementsByTagName("animal_associated");
                    if (associatedAnimals.getLength() > 0) {
                        System.out.println("  Animais Associados:");
                        for (int j = 0; j < associatedAnimals.getLength(); j++) {
                            System.out.println("    - " + tratadorElement.getElementsByTagName("experience").item(j).getTextContent());
                        }
                    }
                }
            }
        } catch (ParserConfigurationException | XPathExpressionException | SAXException | IOException e) {
            System.err.println("Erro ao tentar obter os dados do XML.");
        }
    }
}