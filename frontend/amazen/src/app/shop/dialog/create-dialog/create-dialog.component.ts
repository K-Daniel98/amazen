import { Component, Input, Output, EventEmitter } from '@angular/core';
import { ItemDto } from '../../dto/item.dto';
import { ItemService } from '../../item.service';
import { NbToastrService } from '@nebular/theme';

@Component({
  selector: 'app-create-dialog',
  templateUrl: './create-dialog.component.html',
  styleUrls: ['../common/dialog-common.style.scss'],
})
export class CreateDialogComponent {
  item: ItemDto = {
    name: '',
    price: 0.0,
    description: '',
    id: 0,
    imageUrl: '',
  };
  @Input() ref: any;

  @Output() newItemEvent = new EventEmitter<ItemDto>();

  constructor(
    private readonly itemService: ItemService,
    private readonly toastrService: NbToastrService
  ) {}

  async createItem() {
    try {
      const createdItem = await this.itemService.createItem(this.item);
      this.newItemEvent.emit(createdItem);

      this.toastrService.success(
        'Item has been created',
        'Successful request',
        {
          hasIcon: true,
          icon: 'checkmark-outline',
        }
      );
    } catch (error: unknown) {
      if (error instanceof Error) {
        this.toastrService.danger(error.message, 'Request failed');
      } else {
        this.toastrService.danger('Unexpected error', 'Request failed');
      }
    }

    this.ref.close();
  }
}
