package br.com.fiap.twitterapi;

import java.util.Comparator;
import java.util.Date;

public class Tweet {
	
	private String nome;
	private String primeiroNome;
	private String ultimoNome;
	
	private Date data;
	
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
		
		int index = nome.indexOf(' ');
		if(index >= 0) {
			primeiroNome = nome.substring(0, index);
			ultimoNome = nome.substring(index + 1);
		} else {
			this.primeiroNome = nome;
		}
		
	}
	public String getPrimeiroNome() {
		return primeiroNome;
	}
	@Override
	public String toString() {
		return "Tweet [nome=" + nome + ", primeiroNome=" + primeiroNome + ", ultimoNome=" + ultimoNome + ", data="
				+ data + "]";
	}
	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}
	public String getUltimoNome() {
		return ultimoNome;
	}
	public void setUltimoNome(String ultimoNome) {
		this.ultimoNome = ultimoNome;
	}
	
	
	public static Comparator<Tweet> NomeComparator = new Comparator<Tweet>() {

        @Override
        public int compare(Tweet e1, Tweet e2) {
            return (int) (e1.getNome().compareTo(e2.getNome()));
        }
    };
    
	public static Comparator<Tweet> DataComparator = new Comparator<Tweet>() {

        @Override
        public int compare(Tweet e1, Tweet e2) {
            return (int) (e1.getData().compareTo(e2.getData()));
        }
    }; 
	
}
