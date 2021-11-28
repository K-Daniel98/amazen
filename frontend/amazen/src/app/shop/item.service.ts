import { Injectable } from '@angular/core';
import { HttpResponse, HttpStatusCode } from '@angular/common/http';
import { NbAuthService, NbTokenService } from '@nebular/auth';
import { ItemDto } from './dto/item.dto';

@Injectable()
export class ItemService {
  constructor(private readonly tokenService: NbTokenService) {}

  public async createItem(item: ItemDto): Promise<ItemDto> {
    return (
      await this.sendAuthenticatedRequest('/api/item', 'POST', item)
    ).json();
  }

  public async deleteItem(id: number): Promise<void> {
    const response = await this.sendAuthenticatedRequest(
      '/api/item',
      'DELETE',
      id
    );
    if (response.status !== HttpStatusCode.NoContent) {
      throw new Error('Failed to delete the item');
    }
  }

  public async updateItem(item: ItemDto): Promise<ItemDto> {
    return (
      await this.sendAuthenticatedRequest('/api/item', 'PATCH', item)
    ).json();
  }

  public async loadItems(): Promise<Array<ItemDto>> {
    return (
      await fetch('http://localhost:5100/api/item', {
        method: 'GET',
      })
    ).json();
  }

  private sendAuthenticatedRequest(
    endpoint: string,
    method: string,
    data: any = undefined
  ): Promise<Response> {
    const token = this.tokenService.get();

    return fetch(endpoint, {
      method,
      headers: {
        Authorization: `Bearer ${token}`,
      },
      body: !data ? null : JSON.stringify(data),
    });
  }
}
