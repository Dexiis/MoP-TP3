import Alimentos.*;
import Animais.*;
import Funcionarios.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Classe que trabalha com tudo relacionado ao XML
 * Encarregada de salvar e carregar o projeto.
 */
public class XML {

    /**
     * Salva o estado atual do Zoo, incluindo os seus animais, funcionários, visitantes e stock de alimentos, num ficheiro XML.
     * O ficheiro XML será guardado numa pasta "XML" com o nome especificado.
     *
     * @param zooName      O nome do Zoo a ser guardado.
     * @param zooPrecario  O valor do precário atual do Zoo.
     * @param animais      Uma lista de objetos 'Animal' presentes no Zoo.
     * @param funcionarios Uma lista de objetos 'Funcionario' presentes no Zoo.
     * @param visitantes   Uma lista de objetos 'Visitante' presentes no Zoo.
     * @param carne        O objeto Carne representando o stock de carne no Zoo.
     * @param palha        O objeto Palha representando o stock de palha no Zoo.
     * @param peixe        O objeto Peixe representando o stock de peixe no Zoo.
     * @param ficheiro     O nome base do ficheiro XML a ser criado (sem a extensão .xml).
     */
    public static void salvarZoo(String zooName, int zooPrecario, ArrayList<Animal> animais, ArrayList<Funcionario> funcionarios, ArrayList<Visitante> visitantes, Carne carne, Palha palha, Peixe peixe, String ficheiro) {
        Document documentZoo;
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            documentZoo = dBuilder.newDocument();

            Element rootElement = documentZoo.createElement("zoo");
            rootElement.setAttribute("nome", zooName);
            documentZoo.appendChild(rootElement);

            Element precarioElement = documentZoo.createElement("precario");
            precarioElement.appendChild(documentZoo.createTextNode(Integer.toString(zooPrecario)));
            rootElement.appendChild(precarioElement);

            Element visitantesElement = documentZoo.createElement("visitantes");
            rootElement.appendChild(visitantesElement);

            Element funcionariosElement = documentZoo.createElement("funcionarios");
            rootElement.appendChild(funcionariosElement);

            Element administradoresElement = documentZoo.createElement("administradores");
            funcionariosElement.appendChild(administradoresElement);

            Element tratadoresElement = documentZoo.createElement("tratadores");
            funcionariosElement.appendChild(tratadoresElement);

            Element guiasElement = documentZoo.createElement("guias");
            funcionariosElement.appendChild(guiasElement);

            Element animaisElement = documentZoo.createElement("animais");
            rootElement.appendChild(animaisElement);

            Element leoesElement = documentZoo.createElement("leoes");
            animaisElement.appendChild(leoesElement);

            Element elefantesElement = documentZoo.createElement("elefantes");
            animaisElement.appendChild(elefantesElement);

            Element girafasElement = documentZoo.createElement("girafas");
            animaisElement.appendChild(girafasElement);

            Element pinguinsElement = documentZoo.createElement("pinguins");
            animaisElement.appendChild(pinguinsElement);

            Element alimentosElement = documentZoo.createElement("alimentos");
            rootElement.appendChild(alimentosElement);

            for (Visitante visitante : visitantes) {
                Element novoVisitante = documentZoo.createElement("visitante");
                novoVisitante.setAttribute("ID", Integer.toString(visitante.getID()));

                Element visitanteName = documentZoo.createElement("name");
                visitanteName.appendChild(documentZoo.createTextNode(visitante.getName()));
                novoVisitante.appendChild(visitanteName);

                Element visitanteAge = documentZoo.createElement("age");
                visitanteAge.appendChild(documentZoo.createTextNode(Integer.toString(visitante.getAge())));
                novoVisitante.appendChild(visitanteAge);

                visitantesElement.appendChild(novoVisitante);
            }

            for (Animal animal : animais) {
                Element novoAnimal = documentZoo.createElement(animal.getClass().getSimpleName().toLowerCase());
                novoAnimal.setAttribute("name", animal.getName());

                Element animalAge = documentZoo.createElement("age");
                animalAge.appendChild(documentZoo.createTextNode(Integer.toString(animal.getAge())));
                novoAnimal.appendChild(animalAge);

                Element animalWeight = documentZoo.createElement("weight");
                animalWeight.appendChild(documentZoo.createTextNode(Integer.toString(animal.getWeight())));
                novoAnimal.appendChild(animalWeight);

                Element animalDiet = documentZoo.createElement("diet");
                animalDiet.appendChild(documentZoo.createTextNode(animal.getDiet()));
                novoAnimal.appendChild(animalDiet);

                Element animalType = documentZoo.createElement("type");
                animalType.appendChild(documentZoo.createTextNode(animal.getType()));
                novoAnimal.appendChild(animalType);

                switch (animal) {
                    case Leao leao -> leoesElement.appendChild(novoAnimal);
                    case Elefante elefante -> elefantesElement.appendChild(novoAnimal);
                    case Girafa girafa -> girafasElement.appendChild(novoAnimal);
                    case Pinguim pinguim -> pinguinsElement.appendChild(novoAnimal);
                    default -> {
                    }
                }
            }

            for (Funcionario funcionario : funcionarios) {
                Element novoFuncionario = documentZoo.createElement(funcionario.getClass().getSimpleName().toLowerCase());
                novoFuncionario.setAttribute("ID", Integer.toString(funcionario.getID()));

                Element funcionarioName = documentZoo.createElement("name");
                funcionarioName.appendChild(documentZoo.createTextNode(funcionario.getName()));
                novoFuncionario.appendChild(funcionarioName);

                Element funcionarioAge = documentZoo.createElement("age");
                funcionarioAge.appendChild(documentZoo.createTextNode(Integer.toString(funcionario.getAge())));
                novoFuncionario.appendChild(funcionarioAge);

                Element funcionarioExperience = documentZoo.createElement("experience");
                funcionarioExperience.appendChild(documentZoo.createTextNode(Integer.toString(funcionario.getExperience())));
                novoFuncionario.appendChild(funcionarioExperience);

                if (funcionario instanceof Tratador)
                    for (Animal animal : ((Tratador) funcionario).getAnimaisAssociados()) {
                        Element funcionarioAnimalAssociated = documentZoo.createElement("animal_associated");
                        funcionarioAnimalAssociated.appendChild(documentZoo.createTextNode(animal.toString()));
                        novoFuncionario.appendChild(funcionarioAnimalAssociated);
                    }

                switch (funcionario) {
                    case Administrador administrador -> administradoresElement.appendChild(novoFuncionario);
                    case Tratador tratador -> tratadoresElement.appendChild(novoFuncionario);
                    case Guia guia -> guiasElement.appendChild(novoFuncionario);
                    default -> {
                    }
                }
            }

            Element novaCarne = documentZoo.createElement("carne");
            novaCarne.setAttribute("quantidade", Integer.toString(carne.getQuantidade()));
            alimentosElement.appendChild(novaCarne);

            Element novaPalha = documentZoo.createElement("palha");
            novaPalha.setAttribute("quantidade", Integer.toString(palha.getQuantidade()));
            alimentosElement.appendChild(novaPalha);

            Element novoPeixe = documentZoo.createElement("peixe");
            novoPeixe.setAttribute("quantidade", Integer.toString(peixe.getQuantidade()));
            alimentosElement.appendChild(novoPeixe);

            String nomeDoFicheiro = "XML/" + ficheiro + ".xml";
            try (FileOutputStream fileOut = new FileOutputStream(nomeDoFicheiro)) {
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "BaseDados.dtd");

                DOMSource source = new DOMSource(documentZoo);
                StreamResult result = new StreamResult(fileOut);

                transformer.transform(source, result);
                System.out.println("XML do Zoo construído com sucesso!");
            } catch (IOException | TransformerException e) {
                System.err.println("Erro ao guardar o  XML do Zoo");
            }
        } catch (ParserConfigurationException e) {
            System.err.println("Erro na configuração do XML");
        }
    }

    /**
     * Carrega os dados de um Zoo a partir de um ficheiro XML.
     * O ficheiro é procurado diretamente no diretório atual se o nome for "ZooPredefinido",
     * caso contrário, é procurado na pasta "XML".
     * Os dados carregados incluem o nome do Zoo, preçário, visitantes, animais, funcionários e stock de alimentos.
     *
     * @param ficheiro O nome do ficheiro XML a ser carregado (sem a extensão .xml).
     * @return Um array de Object contendo a instância carregada do Zoo (índice 0) e um booleano indicando
     * se ocorreu um erro durante o carregamento (índice 1). Retorna {@code null} para o Zoo
     * e {@code true} para o erro caso a leitura falhe.
     */
    public static Object[] carregarZoo(String ficheiro) {
        Zoo newZoo = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document;
            if (ficheiro.equals("ZooPredefinido")) {
                document = builder.parse(new File(ficheiro + ".xml"));
            } else {
                document = builder.parse(new File("XML/" + ficheiro + ".xml"));
            }

            Element zooElement = document.getDocumentElement();
            String nameZoo = zooElement.getAttribute("nome");
            int precarioZoo = Integer.parseInt(zooElement.getElementsByTagName("precario").item(0).getTextContent());

            newZoo = Zoo.getZooInstance(nameZoo, precarioZoo);

            NodeList visitantesList = zooElement.getElementsByTagName("visitantes");
            if (visitantesList.getLength() > 0) {
                Element visitantesElement = (Element) visitantesList.item(0);
                NodeList visitanteNodes = visitantesElement.getElementsByTagName("visitante");
                for (int i = 0; i < visitanteNodes.getLength(); i++) {
                    Node visitanteNode = visitanteNodes.item(i);
                    if (visitanteNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element visitanteElement = (Element) visitanteNode;

                        String nameVisitante = visitanteElement.getElementsByTagName("name").item(0).getTextContent();
                        int idVisitante = Integer.parseInt(visitanteElement.getAttribute("ID"));
                        int ageVisitante = Integer.parseInt(visitanteElement.getElementsByTagName("age").item(0).getTextContent());

                        newZoo.addVisitante(nameVisitante, idVisitante, ageVisitante);
                    }
                }
            }

            NodeList animaisListXml = zooElement.getElementsByTagName("animais");
            if (animaisListXml.getLength() > 0) {
                Element animaisContainer = (Element) animaisListXml.item(0);
                String[] animalCollections = {"leoes", "elefantes", "girafas", "pinguins"};
                String[] animalTypes = {"Leao", "Elefante", "Girafa", "Pinguim"};

                for (int colIndex = 0; colIndex < animalCollections.length; colIndex++) {
                    String collectionTagName = animalCollections[colIndex];
                    String animalType = animalTypes[colIndex];
                    NodeList animalSubclassList = animaisContainer.getElementsByTagName(collectionTagName);
                    if (animalSubclassList.getLength() > 0) {
                        Element animalSubclassContainer = (Element) animalSubclassList.item(0);
                        NodeList animalNodes = animalSubclassContainer.getElementsByTagName(animalType.toLowerCase());
                        for (int i = 0; i < animalNodes.getLength(); i++) {
                            Node animalNode = animalNodes.item(i);
                            if (animalNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element animalElement = (Element) animalNode;

                                String nameAnimal = animalElement.getAttribute("name");
                                int ageAnimal = Integer.parseInt(animalElement.getElementsByTagName("age").item(0).getTextContent());
                                int weightAnimal = Integer.parseInt(animalElement.getElementsByTagName("weight").item(0).getTextContent());

                                switch (animalType) {
                                    case "Leao" -> newZoo.addAnimais(nameAnimal, ageAnimal, weightAnimal, "leão");
                                    case "Elefante" ->
                                            newZoo.addAnimais(nameAnimal, ageAnimal, weightAnimal, "elefante");
                                    case "Girafa" -> newZoo.addAnimais(nameAnimal, ageAnimal, weightAnimal, "girafa");
                                    case "Pinguim" -> newZoo.addAnimais(nameAnimal, ageAnimal, weightAnimal, "pinguim");
                                }
                            }
                        }
                    }
                }
            }

            NodeList funcionariosListXml = zooElement.getElementsByTagName("funcionarios");
            if (funcionariosListXml.getLength() > 0) {
                Element funcionariosContainer = (Element) funcionariosListXml.item(0);
                String[] funcionarioCollections = {"administradores", "tratadores", "guias"};
                String[] funcionarioTypes = {"Administrador", "Tratador", "Guia"};

                for (int colIndex = 0; colIndex < funcionarioCollections.length; colIndex++) {
                    String collectionTagName = funcionarioCollections[colIndex];
                    String funcionarioType = funcionarioTypes[colIndex];

                    NodeList funcionarioSubclassList = funcionariosContainer.getElementsByTagName(collectionTagName);
                    if (funcionarioSubclassList.getLength() > 0) {
                        Element funcionarioSubclassContainer = (Element) funcionarioSubclassList.item(0);
                        NodeList funcionarioNodes = funcionarioSubclassContainer.getElementsByTagName(funcionarioType.toLowerCase());
                        for (int i = 0; i < funcionarioNodes.getLength(); i++) {
                            Node funcionarioNode = funcionarioNodes.item(i);
                            if (funcionarioNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element funcionarioElement = (Element) funcionarioNode;

                                String nameFuncionario = funcionarioElement.getElementsByTagName("name").item(0).getTextContent();
                                int ageFuncionario = Integer.parseInt(funcionarioElement.getElementsByTagName("age").item(0).getTextContent());
                                int idFuncionario = Integer.parseInt(funcionarioElement.getAttribute("ID"));
                                int experienceTratador = Integer.parseInt(funcionarioElement.getElementsByTagName("experience").item(0).getTextContent());

                                switch (funcionarioType) {
                                    case "Administrador" ->
                                            newZoo.addFuncionarios(nameFuncionario, ageFuncionario, idFuncionario, experienceTratador, "administrador");
                                    case "Tratador" -> {
                                        newZoo.addFuncionarios(nameFuncionario, ageFuncionario, idFuncionario, experienceTratador, "tratador");
                                        for (int j = 0; j < funcionarioElement.getElementsByTagName("animal_associated").getLength(); j++) {
                                            String[] words = funcionarioElement.getElementsByTagName("animal_associated").item(j).getTextContent().split(" ");
                                            newZoo.setAnimalTratador(idFuncionario, words[2]);
                                        }
                                    }
                                    case "Guia" ->
                                            newZoo.addFuncionarios(nameFuncionario, ageFuncionario, idFuncionario, experienceTratador, "guia");
                                }
                            }
                        }
                    }
                }
            }

            NodeList alimentosList = zooElement.getElementsByTagName("alimentos");
            if (alimentosList.getLength() > 0) {
                Element alimentosContainer = (Element) alimentosList.item(0);

                NodeList carneNodes = alimentosContainer.getElementsByTagName("carne");
                Element carneElement = (Element) carneNodes.item(0);
                int carneQuantidade = Integer.parseInt(carneElement.getAttribute("quantidade"));
                newZoo.addAlimento(carneQuantidade, "carne");

                NodeList palhaNodes = alimentosContainer.getElementsByTagName("palha");
                Element palhaElement = (Element) palhaNodes.item(0);
                int palhaQuantidade = Integer.parseInt(palhaElement.getAttribute("quantidade"));
                newZoo.addAlimento(palhaQuantidade, "palha");

                NodeList peixeNodes = alimentosContainer.getElementsByTagName("peixe");
                Element peixeElement = (Element) peixeNodes.item(0);
                int peixeQuantidade = Integer.parseInt(peixeElement.getAttribute("quantidade"));
                newZoo.addAlimento(peixeQuantidade, "peixe");
            }

            return new Object[]{newZoo, false};
        } catch (ParserConfigurationException | IOException | SAXException e) {
            System.out.println("Erro ao ler o ficheiro XML");
        }
        return new Object[]{newZoo, true};
    }
}
