import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {SortType, StudentSortResult} from '../model/student.model';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  constructor(private http: HttpClient) { }

  public studentSort(file: File, sortType: SortType): Observable<StudentSortResult> {
    const formData: FormData = new FormData();
    formData.append('file', file);
    return this.http.post<StudentSortResult>(`${environment.backendUrl}/student/sort/${sortType}`, formData);
  }

  public exportToFile(file: File, sortType: SortType): Observable<any> {
    const options = {
      headers: new HttpHeaders({
        Accept: 'text/plain'
      }),
      responseType: 'text' as 'json'
    };

    const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
    const formData: FormData = new FormData();
    formData.append('file', file);
    return this.http.post<any>(`${environment.backendUrl}/student/export-to-file/${sortType}`, formData, options);
  }
}
