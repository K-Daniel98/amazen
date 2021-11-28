import { Injectable } from '@angular/core';
import { HttpStatusCode } from '@angular/common/http';
import { NbTokenService } from '@nebular/auth';
import { ItemDto } from './dto/item.dto';

@Injectable()
export class ItemService {
  private static readonly API_URL = 'http://localhost:5100';

  constructor(private readonly tokenService: NbTokenService) {}

  public async createItem(item: ItemDto): Promise<ItemDto> {
    const response = await this.sendAuthenticatedRequest(
      `${ItemService.API_URL}/api/item`,
      'POST',
      item
    );

    if (response.status !== HttpStatusCode.Created) {
      throw new Error((await response.json()).message);
    }

    return response.json();
  }

  public async deleteItem(id: number): Promise<void> {
    const response = await this.sendAuthenticatedRequest(
      `${ItemService.API_URL}/api/item/${id}`,
      'DELETE',
      id
    );

    if (response.status !== HttpStatusCode.NoContent) {
      throw new Error((await response.json()).message);
    }
  }

  public async updateItem(item: ItemDto): Promise<ItemDto> {
    const response = await this.sendAuthenticatedRequest(
      `${ItemService.API_URL}/api/item`,
      'PATCH',
      item
    );

    if (response.status !== HttpStatusCode.Ok) {
      throw new Error((await response.json()).message);
    }

    return response.json();
  }

  public async loadItems(): Promise<Array<ItemDto>> {
    const response = await fetch(`${ItemService.API_URL}/api/item`, {
      method: 'GET',
    });

    if (response.status !== HttpStatusCode.Ok) {
      throw new Error((await response.json()).message);
    }

    return response.json();
  }

  private async sendAuthenticatedRequest(
    endpoint: string,
    method: string,
    data: any = undefined
  ): Promise<Response> {
    const token = (await this.tokenService.get().toPromise()).getValue();

    return fetch(endpoint, {
      method,
      headers: {
        Authorization: `Bearer ${token}`,
        'Content-Type': 'application/json',
      },
      body: !data ? null : JSON.stringify(data),
    });
  }
}
