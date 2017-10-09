import { Component, OnInit } from '@angular/core';
import { ProductItem } from './product-item';
import { ProductService } from './product.service';
import { FullImageViewComponent } from './full-image-view/full-image-view.component';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material';


export class ProdectFeed {
  feedName: string;
}
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';
  productItems: ProductItem[];
  productFeed: ProdectFeed = { feedName: "" };
  selectedProducts: boolean[] = [];
  selectedProductsCount: number = 0;
  allSelected: boolean = false;
  constructor(private productService: ProductService, public dialog: MatDialog) {

  }

  selectAll(): void {
    for (var i = 0; i < this.selectedProducts.length; i++) {
      this.selectedProducts[i] = this.allSelected;
    }

  }
  uploadProductFeed(): void {
    let filtertProducts: ProductItem[] = [];
    for (var i = 0; i < this.productItems.length; i++) {
      if (this.selectedProducts[i]) {
        filtertProducts.push(this.productItems[i]);
      }
    }
    this.productService.uploadProductFeed(this.productFeed.feedName, filtertProducts).then(
      responce => {
        if (responce == true) {
          alert("Successfully uploaded")
        }
      });
  }
  getProductItems(): void {
    this.productService.getProductItems().then(
      products => {
        this.productItems = products
        for (var i = 0; i < products.length; i++) {
          this.selectedProducts.push(false);
        }
      });
  }

  changeProductSelection(ind: number): void {
    //this.selectedProducts[ind] = !this.selectedProducts[ind]; 
    if (this.selectedProducts[ind] == true) {
      this.selectedProductsCount++;
    } else {
      this.selectedProductsCount--;
    }
  }
  ngOnInit(): void {
    this.getProductItems();
  }


  openDialog(url: string, productTitle: string) {
    this.dialog.open(FullImageViewComponent, {

      data: {
        imageUrl: url,
        productTitle: productTitle
      }
    });
  }

}
