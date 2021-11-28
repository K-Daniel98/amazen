import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ItemDto } from '../../dto/item.dto';
import { ItemService } from '../../item.service';
import { NbToastrService } from '@nebular/theme';

@Component({
  selector: 'app-delete-dialog',
  templateUrl: './delete-dialog.component.html',
  styleUrls: ['../common/dialog-common.style.scss'],
})
export class DeleteDialogComponent {
  @Input() name: string = '';
  @Input() id: number = -1;
  @Input() ref: any;

  @Output() deleteItemEvent = new EventEmitter<number>();

  constructor(
    private readonly itemService: ItemService,
    private readonly toastrService: NbToastrService
  ) {}

  async deleteItem() {
    try {
      await this.itemService.deleteItem(this.id);
      this.deleteItemEvent.emit(this.id);

      this.toastrService.warning(
        'Item has been deleted',
        'Successful request',
        {
          hasIcon: true,
          icon: 'alert-triangle-outline',
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
