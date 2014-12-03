/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.trabalho.dados;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author Andre
 */
public class GerarLog {

    public void LogTxt(String evento, String erro) {
        String local = System.getProperty("user.dir");
        File arquivoTxt = new File(local + "\\log_programa.txt");

        Date data = new Date();

        if (!arquivoTxt.exists()) {
            try {   //Cria o arquivo
                arquivoTxt.createNewFile();
                System.out.println("Arquivo criado");

                //salva o arquivo
                FileWriter writer = new FileWriter(arquivoTxt);
                writer.write(evento + "\r\n");
                writer.write(erro + "\r\n");
                writer.write("Data:" + data + "\r\n");
                writer.write("=======================\r\n");
                writer.close();
                System.out.println("Arquivo salvado");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FileReader reader = new FileReader(arquivoTxt);
                BufferedReader br = new BufferedReader(reader);
                String linha = br.readLine();
                FileWriter writer = new FileWriter(arquivoTxt);

                while (linha != null && linha != "") {
                    System.out.println(linha);
                    writer.write(linha + "\r\n");
                    linha = br.readLine();
                }

                br.close();
                reader.close();

                writer.write(evento + "\r\n");
                writer.write(erro + "\r\n");
                writer.write("Data:" + data + "\r\n");
                writer.write("=======================\r\n");
                writer.close();
                System.out.println("Arquivo salvado");
            } catch (IOException err) {
                err.printStackTrace();
            }
        }
    }
}
