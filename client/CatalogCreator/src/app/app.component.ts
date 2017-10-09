import { Component,OnInit } from '@angular/core';
import { ProductItem } from './product-item';
import { ProductService } from './product.service';
import { FullImageViewComponent } from './full-image-view/full-image-view.component';
import {MatDialog, MAT_DIALOG_DATA} from '@angular/material';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';
  productItems: ProductItem[];


  constructor(private productService: ProductService,public dialog: MatDialog) {

  }

  getProductItems(): void {
    this.productService.getProductItems().then(
      products => 
                    this.productItems = products);
  }
  ngOnInit(): void {
    this.getProductItems();
  }


  openDialog(url:string, productTitle:string) {
    this.dialog.open(FullImageViewComponent, {
     
      data: {
        imageUrl: url,
        productTitle: productTitle
      }
    });
  }

}
