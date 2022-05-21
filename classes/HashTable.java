package classes;
import java.util.Random;
public class HashTable {
    /*
    *Atributos da tabela Hash
     */
    public final int tam;   //tamanho da tabela
    public Entrada[] dados; //vetor de Entradas;
    public int pesos[];//vetor de pesos

    /**
     *Método construtor da tabela
     * @param n tamanho da tabela
     *
     */
    public HashTable(int n){
        this.tam = n;
        this.dados = new Entrada[tam];
        this.preencherPesos();

        for(int i = 0; i < this.tam; i++)
            dados[i] = new Entrada();
    }

    /**
     * extrai o codigo de cada char da string e multiplica por um valor de peso no vetor
     * depois somam os resultados que é atribuido a uma variavel de retorno
     * @param chave -> palavra retirada do arquivo
     * @return  codeFinal ->  codigo hash
     */
    public int calcularCodigo(String chave){    //calcula o codigo da chave
        int codeFinal=0;
        for (int i =0; i<chave.length(); i++) {
            int j=i;

            if(i >= pesos.length){
                j=0;
            }

            int code = chave.charAt(i);

            code *= pesos[j];

            codeFinal += code;

        }
        return codeFinal;
    }

    /**
     * PreencherPesos:
     *Método que preenche o vetor de pesos com valores inteiros;
     * @return void
     */
    public void preencherPesos(){
        this.pesos = new  int[5];
        for (int i = 0; i <pesos.length ; i++) {
            this.pesos[i] = (i += 2);
        }
    }

    /**
     * Código hash é recebido e tem seu valor divido por 12
     * @param code -> recebe codigo hash do método "calcularCodigo"
     * @return newwcode -> retorna um segundo hash baseado no primeiro
     */
    public  int segundoHash(int code){
        int newwcode = code/12;
        return newwcode;
    }

    /**
     *  Operação de mapeamento -> Codigo % tam;
     * @param codigo -> RECEBE O CODIGO HASH
     * @return posMapeada -> RETORNO DA POSIÇÃO MAPEADA PARA A TABELA
     */
    public int mapear(int codigo){
        return codigo % tam;    //posicao no mapa e o resto da divisao do codigo pelo tamanho
    }

    /**
     * Tratamento de colisões utilizando dois codigos hash e uma sondagem baseada no número de colisões
     * @param key -> recebe a chave(palavra)
     * @return pos ->a posição da palavra
     */
    public int localizar(String key){
        int calcHash = calcularCodigo(key);
        int pos = mapear(calcHash);//descobre a posicao
        int indiceSondagem = 1; //indice para iniciar a sondagem quadratica
        while(dados[pos].valido && !key.equals(dados[pos].chave)){
            pos = mapear(pos + (indiceSondagem *segundoHash(calcHash)));
            indiceSondagem++;   //indice de sondagem soma + 1
        }
        return pos; //quando acha uma posicao vazia ou com a chave igual, retorna essa posicao
    }

    /**
     * Método para inserir elemento na tabela
     * @param chave -> recebe a chave(palavra)
     * @param novo -> recebe o objeto que contem a palavra
     */
    public void inserir(String chave, StopWord novo){
        Entrada nova = new Entrada(chave, novo);    //cria nova entrada
        int pos = localizar(chave); //localiza a posicao
        dados[pos] = nova;  //posiciona a entrada na respectiva posicao
    }

    /**
     * Buscar / Verificar a existencia de uma palavra na tabela
     * @param chave -> recebe a palavra
     * @return boolean -> true se existir na tabela ou false se não encontrada
     */
    public boolean buscarPalavra(String chave) {
        int pos = localizar(chave); //localiza a posicao da chave
        if (dados[pos].valido) {
            return true;   //retorna o dado dentro da entrada

        }
        else
            return false;
    }

    /**
     *
     * @param pos -> recebe posição da Entrada na tabela
     * @return Entrada -> retorno da entrada relativa a posição na tabela
     */
    public Entrada getDados(int pos) {
        return dados[pos];
    }

    /**
     * Método que gera números aleatorio para buscar na tabela e gerar palavras aleatorias
     * @return String-> palavra aleatoria buscada/gerada apartir da tabela
     */
    public String gerarPalavra(){
        Random rn = new Random(System.currentTimeMillis()*tam);
        int nGerado=0;
        String palavra=" ";
        boolean control = false;

        while (control!=true){

            nGerado = (rn.nextInt(30, 125000));
            //nGerado recebe número aleatorio de 30 - 125.000
            if(getDados(nGerado).valido){//Se existir essa posição na tabela e ela estiver em Uso
                //String palavra recebe palavra relativa a posição da tabela(Ngerado)
                palavra=getDados(nGerado).chave;
                //flag de controle recebe true para quebrar o while
                control=true;
            }
        }
        //retorna palavra aleatoria buscada da tablea
        return palavra;
    }

}
