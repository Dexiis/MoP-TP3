import org.w3c.dom.*;
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
    public static void lookingForEntityList(String entity, String ficheiro) {
        try {
            NodeList entityNodes = pathFinder(entity, ficheiro);

            if (entityNodes.getLength() == 0) {
                System.out.println("Nenhuma entidade encontrada no XML.");
            } else {
                for (int i = 0; i < entityNodes.getLength(); i++) {
                    Node entityNode = entityNodes.item(i);
                    Element entityElement = (Element) entityNode;
                    if (entity.equals("animais") || entity.equals("funcionarios")) {
                        for (int j = 0; j < entityElement.getChildNodes().getLength(); j++) {
                            if (entityElement.getChildNodes().item(j).getNodeType() == Node.ELEMENT_NODE) {
                                Element newEntityElement = (Element) entityElement.getChildNodes().item(j);
                                printEntities(newEntityElement, j);
                            }
                        }
                    } else {
                        printEntities(entityElement, 0);
                    }
                }
            }
        } catch (ParserConfigurationException | XPathExpressionException | SAXException | IOException e) {
            System.err.println("Erro ao tentar obter os dados do XML.");
        }
    }

    public static void lookingForCharacteristic(String entity, String attribute, String attributeRequired, String ficheiro) {
        try {
            NodeList entityNodes = pathFinder(entity, ficheiro);
            if (entityNodes.getLength() == 0) {
                System.out.println("Nenhuma entidade encontrada no XML.");
            } else {
                for (int i = 0; i < entityNodes.getLength(); i++) {
                    Node entityNode = entityNodes.item(i);
                    Element entityElement = (Element) entityNode;

                    if (entityElement.getElementsByTagName(attribute).item(0).getTextContent().equals(attributeRequired)) {
                        if (entity.equals("animais") || entity.equals("funcionarios")) {
                            for (int j = 0; j < entityElement.getChildNodes().item(j).getChildNodes().getLength(); j++) {
                                printEntities(entityElement, j);
                            }
                        } else {
                            printEntities(entityElement, 0);
                        }
                    }
                }
            }
        } catch (ParserConfigurationException | XPathExpressionException | SAXException | IOException |
                 NullPointerException e) {
            e.printStackTrace();
            System.err.println("Erro ao tentar obter os dados do XML.");
        }
    }

    public static void lookingForNumber(String entity, String ficheiro) {
        int counter = 0;
        int animaisCounter = 0;
        String newEntity = entity;
        try {
            if (entity.equals("animais")) newEntity = "leao";
            else if (entity.equals("funcionarios")) newEntity = "tratador";
            NodeList entityNodes = pathFinder(newEntity, ficheiro);
            Element entityElement = (Element) entityNodes.item(0);

            if (entityNodes.getLength() == 0) {
                System.out.println("Nenhuma entidade encontrada no XML.");
            } else {
                if (entity.equals("visitante")) {
                    for (int i = 0; i < entityNodes.getLength(); i++) {
                        counter++;
                    }
                    System.out.println("Existe " + counter + " visitantes encontrados.");
                } else {
                    for (int i = 0; i < entityElement.getParentNode().getParentNode().getChildNodes().getLength(); i++)
                        for (int j = 0; j < entityElement.getParentNode().getParentNode().getChildNodes().item(i).getChildNodes().getLength(); j++)
                            if (entityElement.getParentNode().getParentNode().getChildNodes().item(i).getChildNodes().item(j).getNodeType() == Node.ELEMENT_NODE) {
                                animaisCounter++;
                            }
                    for (int i = 0; i < entityElement.getParentNode().getChildNodes().getLength(); i++)
                        if (entityElement.getParentNode().getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {
                            counter++;
                        }
                    if (entity.equals("animais") | entity.equals("funcionarios"))
                        System.out.println("Existe " + animaisCounter + " " + entityElement.getParentNode().getParentNode().getNodeName() + " dentro do zoo.");
                    else
                        System.out.println("Existe " + animaisCounter + " " + entityElement.getParentNode().getParentNode().getNodeName() + " dentro do zoo dos quais " + counter + " são " + entityElement.getNodeName() + ".");
                }
            }

        } catch (ParserConfigurationException | XPathExpressionException | SAXException | IOException e) {
            System.err.println("Erro ao tentar obter os dados do XML.");
        }
    }

    public static void printWholeZoo(String ficheiro) {
        try {
            String[] entities = {"leao", "elefante", "girafa", "pinguim", "tratador", "administrador", "guia", "visitante"};
            for (String entity : entities) {
                NodeList entityNodes = pathFinder(entity, ficheiro);
                if (entityNodes.getLength() > 0) {
                    for (int i = 0; i < entityNodes.getLength(); i++) {
                        Element entityElement = (Element) entityNodes.item(i);
                        printEntities(entityElement, 0);
                    }
                }
            }
        } catch (ParserConfigurationException | XPathExpressionException | SAXException | IOException e) {
            System.out.println("Erro ao printar o Zoológico!");
        }
    }

    private static void printEntities(Element entityElement, int index) {
        switch (entityElement.getNodeName().trim()) {
            case "animais":
                entityElement = (Element) entityElement.getChildNodes().item(index).getChildNodes();
            case "leao":
            case "elefante":
            case "girafa":
            case "pinguim":
                String nameAnimal = entityElement.getAttribute("name");
                String ageAnimal = entityElement.getElementsByTagName("age").item(0).getTextContent();
                String weightAnimal = entityElement.getElementsByTagName("weight").item(0).getTextContent();
                String dietAnimal = entityElement.getElementsByTagName("diet").item(0).getTextContent();
                String typeAnimal = entityElement.getElementsByTagName("type").item(0).getTextContent();

                System.out.println(entityElement.getNodeName().trim() + " --> Nome: " + nameAnimal + ", Idade: " + ageAnimal + ", Peso: " + weightAnimal + ", Dietia: " + dietAnimal + ", Tipo: " + typeAnimal);
                break;
            case "funcionarios":
                entityElement = (Element) entityElement.getChildNodes().item(index).getChildNodes();
            case "tratador":
            case "guia":
            case "administrador":
                String idEmployee = entityElement.getAttribute("ID");
                String nameEmployee = entityElement.getElementsByTagName("name").item(0).getTextContent();
                String ageEmployee = entityElement.getElementsByTagName("age").item(0).getTextContent();
                String experienceEmployee = entityElement.getElementsByTagName("experience").item(0).getTextContent();

                System.out.println(entityElement.getNodeName().trim() + " --> ID: " + idEmployee + ", Nome: " + nameEmployee + ", Idade: " + ageEmployee + ", Experiência: " + experienceEmployee);

                NodeList associatedAnimals = entityElement.getElementsByTagName("animal_associated");
                if (associatedAnimals.getLength() > 0) {
                    System.out.println("  Animais Associados:");
                    for (int j = 0; j < associatedAnimals.getLength(); j++) {
                        System.out.println("    - " + entityElement.getElementsByTagName("animal_associated").item(j).getTextContent().trim());
                    }
                }
                break;
            case "visitante":
                String idVisitante = entityElement.getAttribute("ID");
                String nameVisitante = entityElement.getElementsByTagName("name").item(0).getTextContent();
                String ageVisitante = entityElement.getElementsByTagName("age").item(0).getTextContent();

                System.out.println(entityElement.getNodeName().trim() + " --> ID: " + idVisitante + ", Nome: " + nameVisitante + ", Idade: " + ageVisitante);
                break;
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
            case "visitante" -> expression = "/zoo/visitantes/visitante";
            case "animais" -> expression = "/zoo/animais/*";
            case "funcionarios" -> expression = "/zoo/funcionarios/*";
            default -> expression = null;
        }
        return expression;
    }

    private static NodeList pathFinder(String entity, String ficheiro) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        Document document;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        document = builder.parse(new File("XML/" + ficheiro + ".xml"));

        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();
        String expression = getExpression(entity);

        return (NodeList) xpath.evaluate(expression, document, XPathConstants.NODESET);
    }
}
