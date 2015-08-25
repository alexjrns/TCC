/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smarties.model.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author Alex
 */
public class Utilitario {
    private static final String key = "4b4ca*1";

    public Utilitario() {
        super();
    }

    public static String MD5(String valor) {
        try {
            valor += key;
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(valor.getBytes());

            return new BigInteger(1, digest.digest()).toString(16).trim();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao calcular MD5 de " + valor, e);
        }
    }

    public String valorXML(String fileXML, String campo) {
        try {
            Element raiz = lerXML(fileXML).getDocumentElement();
            return raiz.getElementsByTagName(campo).item(0).getFirstChild().getNodeValue();
        } catch (ParserConfigurationException e) {
            return Excecoes.erroString(e);
        } catch (SAXException e) {
            return Excecoes.erroString(e);
        } catch (IOException e) {
            return Excecoes.erroString(e);
        }
    }

    private Document lerXML(String arquivoXML) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(arquivoXML);
        return doc;
    }
    
    // Nessa função o parâmetro e do tipo inteiro
    public String diaSemana(int D){
       String dia = "";
        switch(D){
            case 1: dia = "Domingo";
            break;
            case 2: dia = "Segunda-Feira";
            break;
            case 3: dia = "Terça-Feira";
            break;
            case 4: dia = "Quarta-Feira";
            break;
            case 5: dia = "Quinta-Feira";
            break;
            case 6: dia = "Sexta-Feira";
            break;
            case 7: dia = "Sábado";
            break;
        }
        return dia;   
    }
    public static boolean gerarTXT(String caminho, List<String> valor){
        try (FileWriter arquivo = new FileWriter(caminho, true)) {
            PrintWriter gravarArquivo = new PrintWriter(arquivo);
            String aux = "";
            for(String vlString: valor){
                aux += vlString;
            }
            aux += System.getProperty("line.separator");
            gravarArquivo.append(aux);
            arquivo.close();
            return true;
        }catch(IOException e){
            Excecoes.erro(e);
            return false;
        }
    }
}