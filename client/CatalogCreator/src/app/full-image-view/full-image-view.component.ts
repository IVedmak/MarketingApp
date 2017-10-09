import { Component, OnInit,Inject } from '@angular/core';
import {MatDialog, MAT_DIALOG_DATA} from '@angular/material';

@Component({
  selector: 'app-full-image-view',
  templateUrl: './full-image-view.component.html',
  styleUrls: ['./full-image-view.component.css']
})
export class FullImageViewComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public data: any) {}

  ngOnInit() {
  }

}
