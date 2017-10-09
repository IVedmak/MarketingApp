import { BrowserModule } from '@angular/platform-browser';
import {HttpModule} from '@angular/http'
import {FormsModule} from '@angular/forms'
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatGridListModule, MatButtonModule, MatCheckboxModule, MatCardModule,MatSelectModule, MatInputModule, MatIconModule, MatIconRegistry, MatDialogModule } from '@angular/material';

import { AppComponent } from './app.component';
import { ProductComponent } from './product/product.component';
import { ProductService } from './product.service';
import { FullImageViewComponent } from './full-image-view/full-image-view.component';


@NgModule({
  declarations: [
    AppComponent,
    ProductComponent,
    FullImageViewComponent
  ],
  entryComponents:[FullImageViewComponent],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpModule,FormsModule,
    MatGridListModule,MatButtonModule, MatCheckboxModule, MatCardModule,MatSelectModule, MatInputModule, MatIconModule, MatDialogModule
  ],
  providers: [ProductService],
  bootstrap: [AppComponent]
})
export class AppModule { }
