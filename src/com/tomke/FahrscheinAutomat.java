package com.tomke;


public class FahrscheinAutomat {
    Fahrschein fahrschein;
    Integer einzahlungInCent;

    public FahrscheinAutomat(Fahrschein fs, Integer ez) {
        this.fahrschein = fs;
        this.einzahlungInCent = ez;
    }

    private int bezahlen() {
        return fahrschein.preis - einzahlungInCent;
    }

    private void rueckmeldung(int rueckGeld) {
        if (rueckGeld == 0) {
            System.out.println("Ausgestelltes Ticket: " + fahrschein.name);
            System.out.println("Vielen Dank und Gute Fahrt!");
        } else if (rueckGeld < 0) {
            System.out.println("Ausgestelltes Ticket: " + fahrschein.name);
            System.out.println("Rueckgeld: " + -rueckGeld + " Cent");
            System.out.println("Vielen Dank und Gute Fahrt!");
        } else {
            if (fahrschein instanceof KurzstreckenFahrschein) {
                System.out.println("Ein " + ((KurzstreckenFahrschein) fahrschein).vorName + fahrschein.name + " kostet " + fahrschein.preis +" Cent.");
            } else if (fahrschein instanceof LangstreckenFahrschein){
                System.out.println("Ein " + ((LangstreckenFahrschein) fahrschein).vorName + fahrschein.name + " kostet " + fahrschein.preis +" Cent.");
            }else {
                System.out.println("Ein " + fahrschein.name + " kostet " + fahrschein.preis +" Cent.");
            }
            System.out.println("Bitte zahlen Sie den fehlenden Betrag von  " + rueckGeld + " Cent ein.");
        }
    }

    public static void main(String[] args) {
        Integer einwurf = Integer.valueOf(args[0]);
        String streckenBezeichnung = args[1];
        System.out.println("Sie haben einen Fahrschein für eine " + streckenBezeichnung + "-Strecke gewählt.");
        System.out.println("Sie haben " + einwurf + " Cent eingezahlt.");

        Fahrschein fahrschein = null;
        if(streckenBezeichnung.equals("")){
            fahrschein = new Fahrschein();
        }else if (streckenBezeichnung.equals("K")){
            fahrschein = new KurzstreckenFahrschein();
        }
        else if (streckenBezeichnung.equals("L")){
            fahrschein = new LangstreckenFahrschein();
        }
        else {
            System.out.println(" Ungültige Eingabe: "+streckenBezeichnung);
        }

        FahrscheinAutomat fahrscheinAutomat = new FahrscheinAutomat(fahrschein, einwurf);
        int rueckGeld = fahrscheinAutomat.bezahlen();
        fahrscheinAutomat.rueckmeldung(rueckGeld);
    }
}
