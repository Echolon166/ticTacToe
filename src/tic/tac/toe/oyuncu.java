package tic.tac.toe;
import java.util.Scanner;


public class oyuncu {
    private char harf;
    private boolean insanmi;
    private String kullaniciAdi = "";
    
    oyuncu(){
        setHarf('X');
        setInsanmi(true);
    }
    
    oyuncu(boolean insanmiKontrolu){
        setInsanmi(insanmiKontrolu);
        if(this.insanmi){
            setHarf('X');
        }
        else{
            setHarf('O');
        }
    }
    oyuncu(boolean insanmiKontrolu, char kr){
        setInsanmi(insanmiKontrolu);
        setHarf(kr);
    }
    
    public char getHarf(){
        return this.harf;
    }
    private void setHarf(char harf){
        this.harf = harf;
    }
    public boolean getInsanmi(){
        return this.insanmi;
    }
    private void setInsanmi(boolean insanmi){
        this.insanmi = insanmi;
    }
    public String getKullaniciAdi(){
        return this.kullaniciAdi;
    }
    protected void setKullaniciAdi(String kullaniciAdi){
        this.kullaniciAdi = kullaniciAdi;
    }
    
    String oyuncununHamlesiniAl(int boyut){
        if(insanmi){
            return insanOyuncuHamlesiniAl();
        }
        else{
            return bilgisayarHamlesiUret(boyut);
        }
    }
    String insanOyuncuHamlesiniAl(){
        Scanner keyboard = new Scanner(System.in);
        System.out.print(this.kullaniciAdi + ", lütfen oynamak istediginiz kareyi giriniz(Örn; A2): ");
        return(keyboard.nextLine().toUpperCase());
    }
    String bilgisayarHamlesiUret(int boyut){
        char a;
        String hamle = "";
        a = (char )(Math.random()*boyut+65);
        hamle = hamle + a;
        a = (char )(Math.random()*boyut+49);
        hamle = hamle + a;
        return hamle;
    }
    
}
