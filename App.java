import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import classes.*;

public class App {

    public static final String arqName ="words.txt";

    public static void main(String[] args)throws IOException{
        HashTable table = new HashTable(124000);//criar tabela
        Scanner sc = new Scanner(System.in);
        carregarPalavras(table);
        //mostrar palavra do dia
       System.out.println(mostrarPalavraDoDia(table));
       System.out.println(" \n \n \n");
       //menu
       menu(table, sc);

       sc.close();
    }

    /**
     *carregarPalavras -> método que lê as entradas do arquivo texto e verifica
     * se as palavras são de tamanho 5, se  tamanho = 5 -> armazenar na tabela hash
     *
     * @param table recebe tabela hash que possui as palavras catalogadas do arquivo
     * @throws IOException ignorando o tratamento de erros da classe IO
     */
    public static void carregarPalavras(HashTable table) throws IOException{
        Scanner readder = new Scanner(new File(arqName));
        String palavraNalinha;

        while (readder.hasNextLine()){
            palavraNalinha = readder.nextLine();
            if(palavraNalinha.length() == 5) {
                StopWord novoTermoPalavra = new StopWord(palavraNalinha);
                table.inserir(palavraNalinha, novoTermoPalavra);
            }

        }

        readder.close();
    }

    /**
     * mostrarPalavraDoDia -> retorna uma palavra pseudo-aleatoria gerada pela tabela
     * @param table recebe tabela hash que possui as palavras catalogadas do arquivo
     */
    public  static String mostrarPalavraDoDia(HashTable table){
        String retorno;

        retorno = "Palavra Do Dia: "+table.gerarPalavra();
        return retorno;
    }

    /**
     *  Menu para utilização do usuario
     * @param tabela hash table
     * @param sc Scanner(System.in)
     */
    public static void menu(HashTable tabela, Scanner sc){

        int menuOption;
        boolean flag = true;

        do {
            System.out.println("BUSCA PALAVRA -> MENU \n BUSCAR PALAVRA(1) SAIR(0) \nENTRE COM A OPÇÃO DESEJADA");
            menuOption = sc.nextInt();
            switch (menuOption) {

                case 0:
                    flag = false;
                    break;
                case 1:

                    if (tabela.buscarPalavra(receberPalavra(sc))) {
                        System.out.println("Palavra existente\n");
                    } else {
                        System.out.println("Não existente\n");
                    }
                    break;
                default:
                    System.out.println("opção invalida!");
            }
        }
        while (flag);

    }

    /**
     *
     * @param sc Scanner para leitura da palavra
     * @return retorna String -> palavra digitada pelo usuario
     */
    public static String receberPalavra(Scanner sc){
        String palavra;
        System.out.println("BUSQUE UMA PALAVRA DE 5 LETRAS PARA VER SE ELA EXISTE: ");
        palavra = sc.next();

        return palavra;
    }
}
