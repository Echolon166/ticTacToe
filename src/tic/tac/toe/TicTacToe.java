package tic.tac.toe;
import java.util.Scanner;
import java.io.*;


public class TicTacToe {

    public static void main(String[] args) {      //Tanimlamalar yapilir.
        Scanner keyboard = new Scanner(System.in);
        boolean newGame = true;
        String kullaniciAdi = "";
        int boyut = 0;
        oyunTahtasi tahta = new oyunTahtasi();
        oyuncu kullanici = new oyuncu();
        oyuncu AI = new oyuncu();
        String a;
        do{                                                                    //Kullanicidan yeni bir oyunami yoksa kayitli oyunami girmek istedigi alinir.
            System.out.println("Yeni bir oyun acmak istiyorsaniz 1,");          //Eger kayitli oyuna girmek isterse gameSave.txt'den oyunun ozellikleri okunur, nesneler olusturulur.
            System.out.println("Kayitli oyundan devam etmek istiyorsaniz 2 giriniz.");
            a = keyboard.nextLine();
            if(a.equals("2")){
                newGame = false;                   //Yeni oyun olup olmadigini belirleyen degisken
                String fileName = "gameSave.txt";
                String line = null;
                char [][] oynTahtasi;
                try{
                    FileReader fileReader = new FileReader(fileName);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    int i = 0;
                    while((line = bufferedReader.readLine()) != null) {
                        String[] saveParcalar = line.split("\\n");
                        if(i == 0){
                            boyut = Integer.parseInt(saveParcalar[0]);
                        }
                        else if(i == 1){
                            oynTahtasi = new char[boyut][boyut];
                            for(int j=0; j<boyut; j++){
                                for(int k=0; k<boyut; k++){
                                    oynTahtasi[j][k] = saveParcalar[0].charAt(j*boyut+k);
                                }
                            }
                            tahta = new oyunTahtasi(oynTahtasi, boyut);
                        }
                        else if(i == 2){
                            if(saveParcalar[0].equals("O")){
                                kullanici = new oyuncu(true, 'O');
                                AI = new oyuncu(false, 'X');
                                AI.setKullaniciAdi("AI");
                            }
                            else{
                                kullanici = new oyuncu(true);
                                AI = new oyuncu(false);
                                AI.setKullaniciAdi("AI");
                            }
                        }
                        else{                                    
                            kullaniciAdi = saveParcalar[0];
                            kullanici.setKullaniciAdi(kullaniciAdi);
                        }
                        i++;
                    }
                    bufferedReader.close(); 
                }
                catch(FileNotFoundException ex) {                  //Dosya okunurken olusabilecek hatalar ayiklanir. Eger hata alinirsa kullanici yeni bir oyuna yonlendirilir.
                    System.out.println("Kayitli oyun bulunamadi.");   
                    System.out.println("Yeni oyun baslatilacak. Devam etmek icin ENTER'e basiniz.");
                    keyboard.nextLine();
                    newGame = true;
                    System.out.print("Kullanici adini giriniz: ");
                    kullaniciAdi = keyboard.nextLine();
                }
                catch(IOException ex) {
                    System.out.println("Kayitli oyun okunurken hata olustu.");
                    System.out.println("Yeni oyun baslatilacak. Devam etmek icin ENTER'e basiniz.");
                    keyboard.nextLine();
                    newGame = true;
                    System.out.print("Kullanici adini giriniz: ");
                    kullaniciAdi = keyboard.nextLine();
                }
            }
            else if(a.equals("1")){                                 //Eger yeni oyuna girmek isterse gerekli bilgiler alinir.
                System.out.print("Kullanici adini giriniz: ");
                kullaniciAdi = keyboard.nextLine();
            }
        }while(!a.equals("1") && !a.equals("2"));
        
        while(true){
            String koordinat = "A";
            if(newGame == true){                            //Yeni bir oyun baslatiliyor ise gerekli bilgiler alinir, nesneler tanimlanir.
            System.out.print("Hangi harfle oynamak istersiniz?[X/O]: ");
            if(keyboard.nextLine().equals("O")){
                kullanici = new oyuncu(true, 'O');
                kullanici.setKullaniciAdi(kullaniciAdi);
                AI = new oyuncu(false, 'X');
                AI.setKullaniciAdi("AI");
            }
            else{
                kullanici = new oyuncu(true);
                kullanici.setKullaniciAdi(kullaniciAdi);
                AI = new oyuncu(false);
                AI.setKullaniciAdi("AI");
            }
            while(true){
                System.out.print("Tahtanin boyutunu giriniz: ");
                try{
                    boyut = Integer.parseInt(keyboard.nextLine());
                    if(boyut != 0){
                        tahta = new oyunTahtasi(boyut);
                        break;
                    }
                }
                catch (NumberFormatException | NegativeArraySizeException ex){
                    
                }
            }
            }
            boolean oynamasirasi = (kullanici.getHarf() == 'X');     //Her zaman X ilk baslamak uzere, turun kimde oldugu belirlenir.
            System.out.println("Oyun sirasinda istediginiz zaman CIK yazarak oyundan cikabilirsiniz.\nOyuna baslamak icin ENTER'e basiniz.");
            keyboard.nextLine();
            tahta.oyunTahtasiniYazdir();
            while(true){                     //Oyunun oynandigi dongu.
                if(oynamasirasi == true){  //Oyuncunun turu
                    boolean gecerli;
                    do{
                        gecerli = false;
                        koordinat = kullanici.oyuncununHamlesiniAl(boyut);
                        if(koordinat.equals("CIK")){                                  //Eger oyuncu cikmak isterse oyun durdurulur. Kayit etmek isterse oyun kapatilir.
                            System.out.print("Oyunu kaydetmek istermisiniz?[E/H]: ");     //Eger kayit etmek istiyor ise, oyun bilgileri gameSave.txt'ye kaydedilir.
                            if(keyboard.nextLine(). equals("E")){
                                String dosyaAdi = "gameSave.txt";
                                char[][] oyunTahtasi = tahta.getOyunTahtasi();
                                try{
                                    FileWriter fileWriter = new FileWriter(dosyaAdi);
                                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                                    bufferedWriter.write(String.valueOf(tahta.getBoyut()));
                                    bufferedWriter.newLine();
                                    for(int i=0; i<boyut; i++){
                                        for(int j=0; j<boyut; j++){
                                            bufferedWriter.write(oyunTahtasi[i][j]);
                                        }
                                    }
                                bufferedWriter.newLine();
                                bufferedWriter.write(kullanici.getHarf());
                                bufferedWriter.newLine();
                                bufferedWriter.write(kullanici.getKullaniciAdi());
                                bufferedWriter.close();
                                }
                                catch(IOException ex){
                                    System.out.println("Oyununuz kaydedilemedi.");
                                    System.exit(0);
                                }
                                System.out.println("Oyununuz kaydedildi.");
                            }
                            System.exit(0);
                        }
                        try{           //Kullanicidan oynamak istedigi koordinat alinir. Eger yanlis formatta girilirse, bu dongu tekrarlanir.
                            if(!tahta.hamleyiYaz(koordinat, kullanici.getHarf())){
                                gecerli = true;
                            }
                        }
                        catch(ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException ex){
                            gecerli = true;
                        }
                    }while(gecerli);
                    if(tahta.kazanan(kullanici.getHarf())){  //Oyuncunun oyunu kazanip kazanmadigi kontrol edilir.
                        tahta.oyunTahtasiniYazdir();
                        System.out.println(kullanici.getKullaniciAdi() + " KAZANDI!!!");
                        break;
                    }
                }
                else{          //Bilgisayarin turu
                    do{                           //Bilgisayarin hamlesi olusturulur.
                        koordinat = AI.oyuncununHamlesiniAl(boyut);
                    }while(!tahta.hamleyiYaz(koordinat, AI.getHarf()));
                    System.out.println(AI.getKullaniciAdi() + " " + koordinat + " koordinatina oynadi.");
                    if(tahta.kazanan(AI.getHarf())){  
                        tahta.oyunTahtasiniYazdir();
                        System.out.println(AI.getKullaniciAdi() + " KAZANDI!!!");
                        break;
                    }
                }
                tahta.oyunTahtasiniYazdir();
                if(tahta.beraberlikKontrol()){
                    System.out.println("Oyun berabere!!!");
                    break;
                }
                oynamasirasi = (oynamasirasi != true);
            }
            
            System.out.print("Yeniden oynamak istermisiniz?[E/H]: ");  //Eger oyun bitti ise, kullanicidan tekrar oynayip isteyip istemedigi alinir.
            if(!keyboard.nextLine().equals("E")){          //Istemez ise oyun kapatilir, ister ise butun islemler tekrarlanir ve yeni oyun baslatilir.
                break;
            }
            newGame = true;
        }
       
        
    }
}
