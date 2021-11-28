import { Component, OnInit, TemplateRef } from '@angular/core';
import { NbDialogService } from '@nebular/theme';
import { DeleteDialogComponent } from './dialog/delete-dialog.component';
import { ItemDto } from './dto/item.dto';
import { ItemService } from './item.service';
import { NbAuthService } from '@nebular/auth';

@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.scss'],
})
export class ShopComponent implements OnInit {
  elements: Array<ItemDto> = [
    {
      id: 1,
      name: 'test item',
      owner: 'Daniel#12',
      description: 'Item description',
      price: 99.99,
      imageUrl:
        'https://telex.hu/assets/images/20211124/1637769878-temp-kIaAKb_cover@1x.jpg',
    },
  ];

  isLoggedIn: boolean = false;

  itemName: string = '';
  itemPrice: number = 0.0;
  itemDescription: string = '';
  itemImageUrl: string = '';

  constructor(
    private readonly dialogService: NbDialogService,
    private readonly itemService: ItemService,
    private readonly authService: NbAuthService
  ) {}

  async ngOnInit(): Promise<void> {
    this.elements = await this.itemService.loadItems();
    this.isLoggedIn = await this.authService.isAuthenticated().toPromise();
    console.log(this.isLoggedIn);
  }

  openDeleteDialog() {
    this.dialogService.open(DeleteDialogComponent, {
      context: 'this is some additional data passed to dialog',
      hasBackdrop: true,
    });
  }

  open(dialog: TemplateRef<any>, data: any = undefined) {
    console.log(data);
    this.dialogService.open(dialog, { context: { data } });
  }

  async createItem() {
    await this.itemService.createItem({
      name: this.itemName,
      description: this.itemDescription,
      price: this.itemPrice,
      imageUrl: this.itemImageUrl,
    });
  }
}
