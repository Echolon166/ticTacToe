package tic.tac.toe;


public class oyunTahtasi {
    private int boyut;
    private char[][] tahta;
    
    oyunTahtasi(){ }
    oyunTahtasi(int boyut){
        setBoyut(boyut);
        tahta = new char[boyut][boyut];
        for(int i=0; i<boyut; i++){
            for(int j=0; j<boyut; j++){
                this.tahta[i][j] = ' ';
            }
        }
    }    
    oyunTahtasi(char [][] oynTahtasi, int boyut){
        setBoyut(boyut);
        tahta = new char[boyut][boyut];
        for(int i=0; i<boyut; i++){
            System.arraycopy(oynTahtasi[i], 0, this.tahta[i], 0, boyut);
        }
    }
    
    char [][] getOyunTahtasi(){
        return tahta;
    }
    public int getBoyut(){
        return boyut;
    }
    private void setBoyut(int boyut){
        this.boyut = boyut;
    }
    void oyunTahtasiniYazdir(){
        System.out.print("-");
        for(int i=0; i<boyut+1; i++){
            System.out.print("----");
        }
        System.out.println();
        for(int i=0; i<boyut+1; i++){
            for(int j=0; j<boyut; j++){
                if(i == 0){
                    if(j == 0){
                        System.out.print("|   | ");
                    }
                    System.out.print((char)(j+65) + " | ");
                }
                else{
                    if(j == 0){
                        System.out.print("| " + i + " | ");
                    }
                    System.out.print(this.tahta[i-1][j] + " | ");
                }
            }
            System.out.println();
            System.out.print("-");
            for(int k=0; k<boyut+1; k++){
                System.out.print("----");
            }
            System.out.println();
        }
    }
    boolean hamleyiYaz(String koordinat, char oyuncu){
        int i, j;
        j = (int )koordinat.charAt(0) - 65;
        i = (int )koordinat.charAt(1) - 49;
        if(this.tahta[i][j] == ' '){
            this.tahta[i][j] = oyuncu;
            return true;
        }
        return false;
    }
    boolean kazanan(char oyuncu){
        for(int i=0; i<boyut; i++){
            for(int j=0; j<boyut; j++){
                if(tahta[i][j] != oyuncu){
                    break;
                }
                if(j == boyut-1){
                    return true;
                }
            }
        }
        for(int i=0; i<boyut; i++){
            for(int j=0; j<boyut; j++){
                if(tahta[j][i] != oyuncu){
                    break;
                }
                if(j == boyut-1){
                    return true;
                }
            }
        }
        for(int i=0; i<boyut; i++){
            if(tahta[i][i] != oyuncu){
                break;
            }
            if(i == boyut-1){
                return true;
            }
        }
        for(int i=0; i<boyut; i++){
            if(tahta[i][boyut-i-1] != oyuncu){
                break;
            }
            if(i == boyut-1){
                return true;
            }
        }
        return false;
    }
    boolean beraberlikKontrol(){
        for(int i=0; i<boyut; i++){
            for(int j=0; j<boyut; j++){
                if(tahta[i][j] == ' '){
                    return false;
                }
            }
        }
        return true;
    }
    
}