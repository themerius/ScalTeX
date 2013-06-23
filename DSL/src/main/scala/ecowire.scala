package scaltex.ecowiremain

import scaltex.template.ecowire._

object Doc extends EcowireReportBuilder {

  val tip = new Titlepage
  val toc = new TableOfContents(doc)
  val doc = new Document

  set document_name "EcoWire --- Businessplan"
  set institut_name "EcoWire. Intelligente Energielösungen"

  // don't forget in js-console: TableOfContents_areal.resolveTocPageNr([Document_areal])

  EcoWireTitle
  Summary
  Produkt
  Marketing
  Geschaeftsmodell
  Team
  Fahrplan
  ChancenRisiken
  Finanzplan  // only use if python+matplotlib+numpy is installed!
  Literatur

  toc.create

  def main(args: Array[String]) {
    write("_output/ecowire.html")
  }
}

object EcoWireTitle extends Titlepage {

  ++ logo("ecowire.png", "ecowire logo")
  ++ name("")
  ++ title("Businessplan", "Wie wir uns ein Kernkraftwerk sparen können.")
  ++ partner("htwg.jpg", "htwg logo")

}

object Summary extends Document {
  
  ++ § "Executive Summary"                                             $ "exsum"

  ++ txt $"""
  Elektrogeräte verbrauchen Unmengen an Standby-Strom,
  d.h. für einen Vierpersonenhaushalt sind das ca. 500 KWh im Jahr,
  wenn man das aufsummiert könnte man sich quasi ein ganzes Kernkraftwerk sparen.
  Daher ist es sicher für die Endkunden attraktiv, in eine Technologie zu
  investieren, die Standby-Strom vermeidet und vielleicht noch anderen nützliche
  Funktionen mitbringt. Für einen Staat, der an der Energiewende zu ökologisch
  sinnvoller Elektrizität interessiert ist, könnte das ein Grund sein,
  in eine solche Technik zu investieren, die massiv Strom sparen kann.

  <br/><br/>

  Hier geht es um die intelligente Steckdose EcoPlug,
  die automatisch den Standby-Strom einsparen kann und gleichzeitig eine
  genaue Verbrauchsanalyse liefert, sowie Ansteuerung und Regelung der
  Stromanschlüsse ermöglicht.

  <br/><br/>

  In der Bundesrepublik Deutschland werden jährlich ca. 2.55 Millionen
  Wohneinheiten neu gebaut oder saniert. An einer Lösung wie oben angegeben
  könnten geschätzt 420000 Wohneinheiten jährlich Interesse zeigen.
  Beim angepeilten Marktanteil von 20% könnte das für unser Produkt bedeuten,
  dass es bis zu 80000 mal pro Jahr verbaut wird.

  <br/><br/>

  Konkurrenten... und wie wir uns abheben …

  <br/><br/>

  Hauptsächlich soll die Hardware an die Kunden verkauft werden,
  welche aus den EcoPlug Steckdosen selbst, sowie der Basisstation,
  die gleichzeitig als Router fungieren kann, besteht.
  Diese Modelle können von Elektronmonteuren, aber auch von
  Sanierungs- und Hausbaufirmen ins Sortiment aufgenommen werden,
  da die Steckdosen eine fachmännische Installation benötigen.
  Das weitere Herzstück ist die Software, die aber als Open Source
  freigegeben wird; also jene Software die auf der Basisstation bzw. auf den
  Steckdosen selbst läuft. Open Source aus dem Grund, dass es einen
  Mitmacheffekt von allen Seiten ermöglicht wird und so ständig frische
  Ideen einfließen können und es dadurch auch mehr Verbreitung gewinnt,
  da Individualisierungen vorgenommen werden können.

  <br/><br/>

  Das Gründungsteam besteht aus Simon Schneeberger und Sven Hodapp,
  beides innovative Informatiker welche die Energiewende vorantreiben
  wollen und mit intelligenten Produkten den Markt aufwirbeln wollen.
  Damit diese Unternehmung erfolgreich wird, werden allerdings noch
  Experten auf den Gebieten Elektrotechnik, Design und Marketing sowie
  Sales und Buchhaltung benötigt.

  <br/><br/>

  Chancen/Risiken...

  <br/><br/>

  Im ersten Jahr ist die Entwicklung des Produkts angesetzt,
  jedoch nach einem halben Jahr sollen schon erste Prototypen umgesetzt sein.
  Das heißt, erst ab dem ersten Jahr ist mit Umsätzen zu rechnen.
  Bei einer stetig wachsenden Verkaufrate ist nach ca. 20 Monaten die
  Gewinnschwelle zu erwarten.
  In Jahr fünf wird der Umsatz bei ${Finanzplan.umsatz} Millionen € und der
  Gewinn bei ${Finanzplan.gewinn} Millionen € erwartet.
  Um die ersten ${Finanzplan.investMon} Monate
  überleben zu können ist ein Investition von ${Finanzplan.investSum}
  Millionen € nötig.

  <br/><br/>
  """

}

object Produkt extends Document {

  newpage

  ++ § "Das Produkt: EcoPlug"                                          $ "prod"

}

object Marketing extends Document {

  newpage

  var moegl_verkaeufe = 80000

  ++ § "Markt und Wettbewerb"                                          $ "markt"

  ++ txt """
    In diesem Kapitel wird geklärt, welche potentielle Marktgröße für
    unser Produkt EcoPlug möglich ist, wie wir in diese Märkte vordringen
    können und welche Konkurrenten mit ihren Vor- und Nachteilen zu
    erwarten sind.
    <br/><br/>
    In erster Linie werden wir den deutschend Wohnungsmarkt für energetische
    Voll- und Teilsanierungen sowie Neubauten ansprechen.
    <br/><br/>
  """

  ++ §§ "Potenzieller Markt"

  ++ txt $"""
    Unsere drei Hauptmärkte sind:
    <ul>
      <li>Wohnungsbau neuer Wohneinheiten</li>
      <li>Komplettsanierungen bestehender Wohneinheiten</li>
      <li>Teilsanierungen bestehender Wohneinheiten</li>
    </ul>

    <br/>

    Für das Jahr 2016 werden rund 246000 neue Wohnungen in neu errichteten
    Wohngebäuden prognostiziert, wobei die jährliche Wachstumsrate
    der fertiggestellten Wohnungen, wie
    auf Abbilung ${Marketing.figWohnungswachstum.figureNumber} dargestellt,
    bei ca. 7% liegt.[1]
  """

  ++ $ "figWohnungswachstum" figure(
    src="http://www.baulinks.de/webplugin/2013/i/0571.gif",
    desc="Wachstumsrate neuer Wohnungen.[1]"
  )

  ++ txt $"""
    Laut der GdW sind in den Jahren 2005 bis 2010 insgesamt jährlich
    0.91% der Wohneinheiten <em>komplett</em> energetisch saniert
    worden und 5% der Wohneinheiten wurden jählrich <em>teilsaniert</em>.[2]

    <br/><br/>

    Der letzte Mikrozensus im April 2002 ergab, dass es zu diesem Zeitpunkt
    knapp 39 Millionen Wohneinheiten in Deutschland gab.[3]

    <br/><br/>

    Aus dieser Faktenlage kann man eine ganz gute Abschätzung über die
    Marktsituation erlangen.

    <br/><br/>

    <ul>
      <li>Neue Wohneinheiten: rund 246,000</li>
      <li>Komplettsanierungen: rund 351,000</li>
      <li>Teilsanierungen: rund 1,950,000</li>
    </ul>

    <br/>

    Also liegt die potentielle Marktgröße bei 2.55 Millionen Wohneinheiten,
    wobei wir die Annahme treffen, dass nur 50% überhaupt Interesse an
    einem Produkt wie EcoPlug haben und von diesen 50% entscheiden
    sich auch 30% tatsächlich dafür ein solche Produkt einbauen zu wollen.
    Da unsere Firma einen Marktanteil von 20% anpeilt, fallen
    ca. ${moegl_verkaeufe} mögliche Produktverkäufe auf uns.

    <br/><br/>

    Der Markt bleibt beständig im Wachstum, da sich die Bundesregierung
    das Ziel gesetzt hat die CO₂-Emissionen
    zu reduzieren, d.h. bis
    2050 soll der Energiebedarf um 80% gesenkt werden. Um dieses Ziel
    zu erreichen, sollen aus politischem Willen heraus mindestens 2%
    der Wohneinheiten jährlich saniert werden.
    In dieser Hinsicht kann unser Produkt attraktiv sein, ein solches
    ambitioniertes Ziel umzusetzen.[4]

    <br/><br/>

    Zudem sei erwähnt, dass Bürogebäude oder Industrieanlagen selbstverständlich
    noch nicht hier miteinbezogen sind. Hier gäbe es mit Sicherheit auch
    einen Markt, zumindest Bauherren moderner Bürogebäude achten auf intelligente
    Gebäudetechnik.

    <br/><br/>
  """

  ++ §§ "Marktdurchdringunsstrategie"

  ++ txt $"""
    Da unser Hauptmarkt auf dem der Wohungen ist, sind unsere Haupthandelspartner
    jene Firmen, die energetische Sanierungen vornehmen bzw. Firmen die
    neue Wohnungen planen und bauen.
    Eine mögliche Strategie ist, sich diesen Firmen bekannt zu machen.
    Also muss unser Marketing entsprechend mit diesen Firmen kontakt aufnehmen,
    und ihnen hochwertiges Informaterial oder kostenlose Prototypen zur
    Verfügung stellen und soweit möglich auch Weiterbildungsveranstaltungen,
    in denen die Produkte vorgeführt werden oder gezeigt wird, wie diese
    sachgemäß installiert werden.

    <br/><br/>

    Zudem könnten noch Elektrofachbetriebe miteinbezogen werden, da diese
    auch die Möglichkeiten haben dem Endkunden das Produkt zu installieren
    bzw. eine Teilsanierung vorzunehmen. Für diese Betriebe ist es sicherlich
    auch noch ein interessantes neues Standbein, durch Verkäufe von EcoPlug
    plus den Einbauauftrag des Kunden; sie können sich dadruch energetische
    Teilsanierungen durchführen, was sonst nur anderen Handwerkern vorbehalten
    blieb.
    <br/><br/>
  """

  ++ §§ "Wettbewerber"

  ++ txt $"""
    Die Konkurrenz bietet momentan Steckdosen, die man in eine normale
    Steckdose stecken kann, aber auch diese können auf ein Modell umsteigen,
    dass EcoWire ähnlich ist. Hier eine Liste der aktuellen
    bzw. wichtigsten Wettbewerber:

    <br/><br/>

    <ul>
      <li>AVM: Fritz!Powerline 546E</li>
      <li>RWE: SmartHome Power Control</li>
      <li>Swisscom Smart Living: Quing</li>
      <li>Arktis: iConnect SmartHome</li>
      <li>Belkin: WeMo Home</li>
      <li>iHomeLab Hochschule Luzern: uFeel</li>
      <li>etc.</li>
    </ul>

    <br/><br/>
  """

}

object Geschaeftsmodell extends Document {

  newpage

  var umsatz_pro_verkauf = 360
  var gewinn_pro_verkauf = 180
  var kosten_pro_verkauf = 144

  ++ § "Geschäftsmodell und Organisation"                              $ "gmodl"

  ++ txt $"""
    Die zwei Kerne unseres Geschäftsmodells sind auf Abbildung
    ${Geschaeftsmodell.figGeschaeftsmodell.figureNumber} grafisch
    veranschaulicht.

    <br/><br/>

    Ein Kern unseres Geschäftsmodells ist der Verkauf von unserer Hardware,
    also die EcoPlug-Steckdosen mit der zugehörigen Basisstation, welche
    die Steckdosen steuert. Diese Hardware ist wie im Kapitel
    ${Marketing.markt.sectionNumber} schon beschrieben hauptsächlich für
    Wohneinheiten gedacht, die eine Teilsanierung oder Komplettsanierung
    vornehmen möchten, sowie für Neubauten. Für gewerbliche bzw. große Kunden
    bieten wir Wartungsverträge für erweiterte Unterstützung und
    Weiterentwicklungsgarantie.

    <br/><br/>

    Zudem gibt es noch die Software die auf den Steckdosen sowie auf
    der Basisstation läuft als Open Source Software.
    In der Software der Basisstation steckt die eigentliche Intelligenz
    unseres Systems, denn dort kann das Monitoring, die Steuerung und
    Analyse vorgenommen werden.
    Warum haben wir uns für <em>Open Source</em> entschieden, obwohl
    ein Großteil unseres Unternehmensschatzes in dieser Software liegt?
    Der Hauptgrund ist der „Mitmacheffekt“ der durch die Community entsteht
    und somit viele neue und innovative Ideen in unser Produkt einfließen
    können---ohne dass wir alle selbst entwickeln müssen.
    Man kann sich auch vorstellen, dass so unsere Software leichter für
    Forschungsprojekte zugänglich ist und damit stetig weitere Innovationen
    einfließen können.
    <br/>
    Zudem ist eine höhere Verbreitung zu erwarten, eben dadruch, dass es
    öffentlich zugänglich ist. Es ermöglicht weiterhin auch den Kunden
    eigene Individualisierungen vorzunehmen, aber auch entsprechende
    Individualisierungsaufträge an unsere Entwicklungsabteilung zu vergeben.
    Auch vorstellbar ist, dass ein Kunde unsere Produkte und Software um
    eigene Funktionen erweitert und weiterverkauft---die Weiterentwicklungen
    an der Software landen aber schlussenldich wieder in unseren Produkt.
  """

  ++ $ "figGeschaeftsmodell" figure1110(
    src="Geschaeftsmodell.png",
    desc="Geschäftsmodell veranschaulicht."
  )


  ++ §§ "Kostenstruktur"

  ++ txt $"""
    Auf Abbildung ${Geschaeftsmodell.figKostenTabelle.figureNumber}
    ist eine Tabelle mit der Aufschlüsselung der Kostenstruktur
    abgebildet.

    <br/><br/>

    Da sich unsere Verkäufe hauptsächlich an Wohneinheiten richten,
    haben wir zur einfachereren Modellierung die Annahme getroffen,
    dass die durchschnittliche Wohneinheit <em>3.5 Zimmer</em> hat
    und dass ein Zimmer mindestens <em>drei Steckdosen sowie einen
    Lampenanschluss<em> hat. Somit kommt man auf pro Verkauf auf
    <em>14 Steckdosen + eine Zentrale</em>, wir nennen diese
    Konstellation—unser durchschnittliches Verkaufspaket—
    <b>14+1</b>.
  """

  ++ $ "figKostenTabelle" figure(
    src="KostenTabelle.png",
    desc="Kostenstruktur für EcoPlug."
  )

  def marge = 1.0 * umsatz_pro_verkauf / kosten_pro_verkauf * 100 - 100

  ++ txt $"""
    Für den durchschnittlichen 14+1 EcoPlug-Verkauf bedeutet das ein
    Umsatz von ${umsatz_pro_verkauf} € und Kosten in Höhe von
    ${kosten_pro_verkauf} €. Somit liegt die Marge pro
    Verkauf des 14+1 Paketes bei ${marge}%.
  """

}

object Team extends Document {

  newpage

  ++ § "Team, Management und Personal"                                 $ "team"

}

object Fahrplan extends Document {

  newpage

  ++ § "Realisierungsfahrplan"                                         $ "plan"

}

object ChancenRisiken extends Document {

  newpage

  ++ § "Chancen und Risiken"                                           $ "chri"

}

object Finanzplan extends Document {

  newpage

  ++ § "Finanzplan und Finanzierung"                                   $ "finanz"

  ++ txt $"""
    Wir haben 5 Jahre an Finanzverlauf geplant, mit Hilfe unseres Finanzmodells.
    Dabei bestehen unsere Fixkosten zum einen aus den Kosten für die Mitarbeiter
    und zum anderen aus allgemeinen Betriebskosten; welche mit dem Wachstum
    des Unternehmens skalieren.

    <br/><br/>

    Zu den allgemeinen Betriebskosten zählen:
    <ul>
      <li>Prototypentwicklung</li>
      <li>Auftraege an Ingeneurburos</li>
      <li>Prototypen als Geschenke an potentielle Kunden</li>
      <li>Flyer, Infomaterial, Webseite und Werbung</li>
      <li>Infoveranstaltungen und Messe</li>
    </ul>

    <br/>

    Zu den Mitarbeiterkosten zählen:
    <ul>
      <li>Gehälter, Abgaben und Steuern</li>
      <li>Weiterbildung</li>
      <li>Software, Büro bzw. Werkstatt und Ausstattung</li>
      <li>Telefon, Firmenwagen und Reisen</li>
    </ul>

    <br />

    Dabei haben wir angenommen, dass wir im Mittel ein 14+1 Paket verkaufen,
    was einen Umsatz von ${Geschaeftsmodell.umsatz_pro_verkauf} € genieriert
    und ${Geschaeftsmodell.kosten_pro_verkauf} € Kosten verursacht,
    für Details siehe Kapitel ${Geschaeftsmodell.gmodl.sectionNumber}.
    Wie im Kapitel ${Marketing.markt.sectionNumber} festgestellt, gibt es
    einen potentiellen Markt von ca. ${Marketing.moegl_verkaeufe} Wohneinheiten
    pro Jahr.
    Diese Zahl wollen wir innerhalb von 5 Jahren erreichen.

    <br/>

    Auf Abbildung ${Finanzplan.figFinanzplan.figureNumber} ist der Umsatz,
    sowie der Gewinn im monatlichen Verlauf aufgetragen, der dem o.g.
    Modell entspricht.
  """

  /*
   * FINANZMODELL RECHNUNG. VARIABLEN UND PLOTS
   */

  val Finanzmodell = $"""
    # -*- coding: utf-8 -*-
    import base64
    import numpy as np
    import matplotlib.pyplot as plt

    class Finanzmodell(object):

        def __init__(self, monate):
            self.umsatz_pro_verkauf = ${Geschaeftsmodell.umsatz_pro_verkauf}.
            self.gewinn_pro_verkauf = ${Geschaeftsmodell.gewinn_pro_verkauf}.
            self.kosten_pro_verkauf = ${Geschaeftsmodell.kosten_pro_verkauf}.

            self.mitarbeiter = 7.
            self.betriebskosten_koeffizient = 1.0

            self.wachstum = 1.016  # Monatlich

            self.umsatz = []
            self.gewinn = []
            self.verkaeufe = []

            self.calc(monate)

        def fixkosten(self, koeffizient):
            fixkosten = self.mitarbeiter * (100000. / 12)
            fixkosten += self.mitarbeiter * (25000. / 12)
            fixkosten += koeffizient * (500000 / 12)
            return fixkosten

        def calc(self, n):
            verkaeufe = 0.
            for idx in range(n):
                self.betriebskosten_koeffizient = self.betriebskosten_koeffizient * self.wachstum
                self.mitarbeiter = self.mitarbeiter * self.wachstum

                um = verkaeufe * self.umsatz_pro_verkauf
                self.umsatz.append(um)

                gw = um
                gw -= self.fixkosten(self.betriebskosten_koeffizient)
                gw -= verkaeufe * self.kosten_pro_verkauf
                self.gewinn.append(gw)

                self.verkaeufe.append(verkaeufe)

                if idx < 12:
                    verkaeufe = (verkaeufe) * self.wachstum
                else:
                    verkaeufe = (verkaeufe * self.wachstum) + 99

        def get_umsatz(self):
            return np.array(self.umsatz) / 1000000.

        def get_gewinn(self):
            return np.array(self.gewinn) / 1000000.

        def get_invest(self):
            summe = 0.
            monate = 0
            for item in self.get_gewinn():
                if item <= 0:
                    summe += item
                    monate += 1
            return {"summe": -summe, "monate": monate}

        def get_lastyear(self):
            um = 0.
            for item in self.umsatz[len(self.umsatz)-12:]:
                um += item

            gw = 0.
            for item in self.gewinn[len(self.gewinn)-12:]:
                gw += item

            vk = 0.
            for item in self.verkaeufe[len(self.verkaeufe)-12:]:
                vk += item

            return {"umsatz": um/1000000, "gewinn": gw/1000000, "verkaeufe": vk}

    modell = Finanzmodell(monate=60)
  """

  val plot = Finanzmodell() + """
    umsatz = modell.get_umsatz()
    gewinn = modell.get_gewinn()

    fig = plt.figure()
    ax = fig.add_subplot(111)
    umsatz_line = ax.plot(range(len(umsatz)), umsatz, label="Umsatz")
    gewinn_line = ax.plot(range(len(gewinn)), gewinn, label="Gewinn")
    plt.legend(bbox_to_anchor=(0., 1.02, 1., .102), loc=3,
       ncol=2, mode="expand", borderaxespad=0.)
    ax.grid(True)
    plt.xticks(np.arange(0,len(umsatz),4))
    plt.ylabel(u"in Millionen €")
    plt.xlabel("Monate")

    # save to document:
    plt.savefig("_output/plot.png", format="png")
    with open("_output/plot.png", "rb") as img:
        print "data:image/png;base64," + base64.b64encode(img.read())
    """

  val res = Finanzmodell() + """
    res = {
      "invest": modell.get_invest(),
      "lastyear": modell.get_lastyear()
    }
    print res
    """


  val pyPlot = new scaltex.addons.PythonScript(plot)

  val results = new scaltex.addons.PythonScript(res).run
  val json = play.api.libs.json.Json.parse(results.replace("'", "\""))

  val investSum = (json \ "invest" \ "summe").as[BigDecimal].setScale(2, BigDecimal.RoundingMode.HALF_UP)
  val investMon = (json \ "invest" \ "monate").as[Int]
  val verkaeufe = (json \ "lastyear" \ "verkaeufe").as[Int]
  val umsatz = (json \ "lastyear" \ "umsatz").as[BigDecimal].setScale(2, BigDecimal.RoundingMode.HALF_UP)
  val gewinn = (json \ "lastyear" \ "gewinn").as[BigDecimal].setScale(2, BigDecimal.RoundingMode.HALF_UP)

  // PLOT

  ++ $ "figFinanzplan" figure1110(
    src=pyPlot.run,
    desc="Finanzplan mit Umsatz und Gewinn."
  )

  /*
   * ENDE FINANZMODELL
   */

  ++ txt $"""
    Wie man auf Abbildung ${Finanzplan.figFinanzplan.figureNumber} erkennen kann
    wird im ersten Jahr kein Umsatz generiert, was liegt daran, dass dort
    die Produketentwicklung stattfindet, sowie die Bekanntmachung des Unternehmens.
    In dieser Zeit sollen also auch die Auftragsbücher gefüllt werden, indem
    die Marketingabteilung Werbung macht und Prototypen herausgibt, so dass
    gerade Sanierungs- und Hausbaufirmen einen Eindruck für das Produkt bekommen.
    <br/>
    Nach dem ersten Jahr ist das Produkt marktreif und kann verkauft werden
    und steigert jeden Monat seine Verkaufszahlen, bis im letzen Jahr die
    Zahl, gemäß unseres Finanzmodells, von ca. ${verkaeufe} erreicht wird,
    was der Wunschzahl von ca. ${Marketing.moegl_verkaeufe} sehr nahe kommt.
    <br/><br/>
  """


  ++ §§ "Investition"

  ++ txt $"""
    Da die Gewinnschwelle nach ca. ${investMon} Monaten zu erwarten ist,
    wird eine Investition nötig sein. Laut unseres Finanzmodells,
    welches in Abbildung ${Finanzplan.figFinanzplan.figureNumber}
    grafisch dargestellt wird, wird eine Investitionssumme von ingesamt
    <em>${investSum} Millionen €</em> benötigt, um die ersten ${investMon} Monate
    flüssig zu bleiben und damit als Firma zu überleben.
    <br/>
    Unser Modell ist für 5 Jahre ausgelegt und darauf optimiert unsere
    gesetzten Verkaufsziele zu erreichen; demnach ist mit einem Umsatz von
    <em>${umsatz} Millionen €</em> im letzten bzw. fünften Jahr zu rechnen,
    sowie mit einem Gewinn von <em>${gewinn} Millionen €</em>.
    <br/><br/>
  """

}

object Literatur extends Document {

  newpage

  ++ § "Quellen"

  ++ txt $"""
    <table>
    <tr>
      <td>[1]&nbsp;</td>
      <td>http://www.baulinks.de/webplugin/2013/0571.php4</td>
    </tr>
    <tr>
      <td>[2]&nbsp;</td>
      <td>http://www.enbausa.de/finanzierung-beratung/aktuelles/artikel/wohnungswirtschaft-legt-energieprognose-2050-vor-3271.html</td>
    </tr>
    <tr>
      <td>[3]&nbsp;</td>
      <td>http://www.schader-stiftung.de/wohn_wandel/823.php</td>
    </tr>
    <tr>
      <td>[4]&nbsp;</td>
      <td>http://www.diw.de/documents/publikationen/73/diw_01.c.377834.de/11-34.pdf</td>
    </tr>
    </table>
  """
}
