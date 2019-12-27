import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-panel',
  templateUrl: './panel.component.html',
  styleUrls: ['./panel.component.css']
})
export class PanelComponent implements OnInit {

  constructor(private title: Title) { 
    this.title.setTitle("Panel")
  }

  ngOnInit() {    
  }

}
