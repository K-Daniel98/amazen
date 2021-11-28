import { Component, OnInit, TemplateRef } from '@angular/core';
import { NbDialogService } from '@nebular/theme';
import { DeleteDialogComponent } from './dialog/delete-dialog/delete-dialog.component';
import { ItemDto } from './dto/item.dto';
import { ItemService } from './item.service';
import { NbAuthService, NbTokenService } from '@nebular/auth';
import { Router } from '@angular/router';

@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.scss'],
})
export class ShopComponent implements OnInit {
  elements: Array<ItemDto> = [];

  isLoggedIn: boolean = false;

  user: any = {
    email: '',
  };

  constructor(
    private readonly dialogService: NbDialogService,
    private readonly itemService: ItemService,
    private readonly authService: NbAuthService,
    private readonly tokenService: NbTokenService,
    private readonly router: Router
  ) {}

  async ngOnInit(): Promise<void> {
    this.elements = await this.itemService.loadItems();
    this.isLoggedIn = await this.authService.isAuthenticated().toPromise();

    this.user.email = (
      await this.tokenService.get().toPromise()
    ).getPayload().sub;
  }

  open(dialog: TemplateRef<any>, data: any = undefined) {
    this.dialogService.open(dialog, { context: { data } });
  }

  addItem(item: ItemDto) {
    this.elements.push(item);
  }

  updateItem(item: ItemDto) {
    const index = this.elements.findIndex((elem) => item.id === elem.id);
    this.elements[index] = item;
  }

  deleteItem(id: number) {
    const index = this.elements.findIndex((elem) => id === elem.id);
    this.elements.splice(index, 1);
  }

  async logout() {
    const result = await this.authService.logout('email').toPromise();

    if (result.isSuccess()) {
      await this.login();
    }
  }

  login() {
    return this.router.navigate(['/auth/login']);
  }
}
