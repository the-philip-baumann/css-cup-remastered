import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  volleyballRules = [
    "Spieldauer: 10 Minuten auf Punkte (ohne Seitenwechsel)",
    "Mannschaften: 4 Spieler (Frauen- & Männeranteil beliebig)",
    "Auswechselspieler erlaubt, es muss jedoch durchrotiert werden",
    "Nur Mitarbeiter welche mindestens 2 Arbeitstage pro Woche bei der CSS oder der",
    "Sanagate arbeiten, können teilnehmen",
    "Feldgrösse: 7x7 Meter / Netzhöhe: 2.35m (mixed)",
    "Gespielt wird auf Beachvolleyball Feldern",
    "Sieg 3 Punkte / Unentschieden 1 Punkt",
    "Bei Punktegleichheit zählt zuerst die Direktbegegnung, anschliessend zählt das bessere Punkteverhältnis"
  ]

  footballRules = [
    "Spieldauer: 10 Minuten (ohne Seitenwechsel)",
    "Mannschaften: 5 Feldspieler und ein Torhüter",
    "(min. eine Frau muss auf dem Platz sei)/ Auswechselspieler erlaubt",
    "Nur Mitarbeiter welche mindestens 2 Arbeitstage pro Woche bei der CSS oder der Sanagate arbeiten, können teilnehmen.",
    "Keine Stollenschuhe erlaubt / Schienbeinschoner Pflicht!",
    "Die erstgenannte Mannschaft spielt Richtung Clubhaus und hat Anspiel",
    "Bei Punktegleichheit zählt zuerst die Direktbegegnung, anschliessend zählt die bessere Tordifferenz",
    "Sieg 3 Punkte / Unentschieden 1 Punkt",
    "Offside-Regelung wird aufgehoben",
    "Rückpassregelung wird nicht angewandt",
    "Bei gelber Karte 5 Minuten Ausschluss",
    "Goali Auskicken bis zur Mittellinie erlaubt."
  ]

  anfahrtInfos = [
    "Adresse: Sportplatz Seefeld, Seebadstrasse 1, 6048 Horw",
    "Mit dem ÖV, Bus Nr. 20, bis Haltestelle: Horw Werkhof",
    "Mit dem Auto: Eingeschränkte Anzahl Parkplätze vorhanden (kostenpflichtig)"
  ]

  constructor() { }

  ngOnInit(): void {
  }



}
