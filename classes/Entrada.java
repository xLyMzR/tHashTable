package classes;

/**
 * @class Entrada(sentinelas da tabela hash que vão receber como atributo a chave e o objeto que contem a palavra)
 * @author lmr
 */
public class Entrada {

    public String chave;//chave da palavra
    public StopWord palavra;// objeto que contem a palavra
    public boolean valido;//status do sentinela(entrada)

    /**
     * Método construtor vazio dda classe Entrada
     */
    public Entrada(){
        this.chave = "";
        this.palavra = null;
        this.unsetValido();
    }

    /**
     *
     * @param palavra recebe a chave -> neste caso a propria palavra
     * @param termoDaPalavra -> recebe o objeto referente a palavra
     * Status da entrada é modificado para valido
     * */
    public Entrada(String palavra, StopWord termoDaPalavra){
        this.chave = palavra;
        this.palavra = termoDaPalavra;
        this.setValido();
    }

    /**
     *
     * @return  boolean -> Verificar o estado da entrada
     */
    public boolean isValido() {
        return valido;
    }
    /**
     *Setvalido
     * this.valido -> Entrada recebe validez = true
     */
    public void setValido(){
        this.valido = true;
    }
    /**
     *SetInvalido
     * this.valido -> Entrada recebe validez = false
     */
    public void unsetValido(){
        this.valido = false;
    }

}
