import { Component, EventEmitter, Input, Output, OnInit } from '@angular/core';
import { ItemDto } from '../../dto/item.dto';
import { ItemService } from '../../item.service';
import { NbToastrService } from '@nebular/theme';

@Component({
  selector: 'app-update-dialog',
  templateUrl: './update-dialog-component.html',
  styleUrls: ['../common/dialog-common.style.scss'],
})
export class UpdateDialogComponent implements OnInit {
  @Input() item: ItemDto = {
    name: '',
    price: 0.0,
    description: '',
    id: 0,
    imageUrl: '',
  };
  @Input() ref: any;

  @Output() updateItemEvent = new EventEmitter<ItemDto>();

  ngOnInit() {
    this.item = { ...this.item };
  }

  constructor(
    private readonly itemService: ItemService,
    private readonly toastrService: NbToastrService
  ) {}

  async update() {
    try {
      const updatedItem = await this.itemService.updateItem(this.item);
      this.updateItemEvent.emit(updatedItem);

      this.toastrService.info('Item has been updated', 'Successful request', {
        hasIcon: true,
        icon: 'info-outline',
      });
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
