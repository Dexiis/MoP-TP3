import Alimentos.*;
import Animais.*;
import Funcionarios.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.TransformerException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;


public class XMLBuilder {

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
                Element novoAnimal = documentZoo.createElement("animal");
                novoAnimal.setAttribute("name", animal.getName());

                Element animalAge = documentZoo.createElement("age");
                animalAge.appendChild(documentZoo.createTextNode(Integer.toString(animal.getAge())));
                novoAnimal.appendChild(animalAge);

                Element animalWeight = documentZoo.createElement("weight");
                animalWeight.appendChild(documentZoo.createTextNode(Integer.toString(animal.getWeight())));
                novoAnimal.appendChild(animalWeight);

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
                Element novoFuncionario = documentZoo.createElement("funcionario");
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
                    for (Object animal : ((Tratador) funcionario).getAnimaisAssociados()) {
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
                writeXml(documentZoo, fileOut);
                System.out.println("XML do Zoo construído com sucesso!");
            } catch (IOException | TransformerException e) {
                System.err.println("Erro ao guardar o  XML do Zoo");
            }
        } catch (ParserConfigurationException e) {
            System.err.println("Erro na configuração do XML");
        }
    }

    private static void writeXml(Document doc, OutputStream output) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "XML/BaseDados.dtd");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);
    }
}