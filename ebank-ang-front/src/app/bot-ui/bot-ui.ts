import { map, Observable } from 'rxjs';
import { CommonModule } from '@angular/common';
import {
  HttpClient,
  HttpDownloadProgressEvent,
  HttpEventType,
  HttpInterceptorFn,
} from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MarkdownModule } from 'ngx-markdown';
import { Loading } from '../services/loading';

@Component({
  selector: 'app-bot-ui',
  imports: [CommonModule, FormsModule, MarkdownModule],
  templateUrl: './bot-ui.html',
  styleUrl: './bot-ui.css',
})
export class BotUi {
  query: any;
  http = inject(HttpClient);
  response$!: Observable<any>;
  public loadService = inject(Loading);

  askAgent() {
    this.response$ = this.http.get('http://localhost:9999/EBANK-BOT/chat?query=' + this.query, {
      responseType: 'text',
    });
  }

  askAgentStream() {
    this.response$ = this.http
      .get('http://localhost:9999/EBANK-BOT/chatStream?query=' + this.query, {
        responseType: 'text',
        observe: 'events',
        reportProgress: true,
      })
      .pipe(
        map((event) => {
          switch (event.type) {
            case HttpEventType.Sent:
              return { type: 'sent' };
            case HttpEventType.DownloadProgress:
              return {
                type: 'Response',
                content: (event as HttpDownloadProgressEvent).partialText,
              };
            case HttpEventType.Response:
              return { type: 'Response', content: event.body };
            default:
              return { type: 'other', data: event };
          }
        }),
      );
  }
}
