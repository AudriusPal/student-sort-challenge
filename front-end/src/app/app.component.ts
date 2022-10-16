import {Component, ViewChild} from '@angular/core';
import {StudentService} from './services/student.service';
import {SortType, Student} from './model/student.model';
import {MatTableDataSource} from '@angular/material/table';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  dataSource = new MatTableDataSource<Student>([]);
  displayedColumns = ['name', 'performance'];

  sortDurationInMs: number;
  numberOfRecords: number;
  file: File;

  @ViewChild('select') select: HTMLSelectElement;

  constructor(private studentService: StudentService) {
  }

  getAlgorithmSelectedValue(): SortType {
    // @ts-ignore
    const text = this.select.nativeElement.value;
    return (SortType)[text];
  }

  onClickImportFile(event: any): void {
    if (event.target.files &&  event.target.files[0]){
     this.file =  event.target.files[0];
     this.studentService.studentSort(this.file, SortType.NONE).subscribe(studentSortResult => {
        this.sortDurationInMs = studentSortResult.sortDurationInMs;
        this.numberOfRecords = studentSortResult.numberOfRecords;
        this.dataSource.data = studentSortResult.sortedList;

      }, error => {
        alert(error.message);
      });
    }
  }

  onClickSort(): void {
    this.studentService.studentSort(this.file, this.getAlgorithmSelectedValue()).subscribe(studentSortResult => {
      this.sortDurationInMs = studentSortResult.sortDurationInMs;
      this.numberOfRecords = studentSortResult.numberOfRecords;
      this.dataSource.data = studentSortResult.sortedList;

    }, error => {
      alert(error.message);
    });
  }

  onClickExportToFile(): void {
    this.studentService.exportToFile(this.file, this.getAlgorithmSelectedValue()).subscribe(data => {
      const blob = new Blob([data], { type: 'text/plain'});
      const url = window.URL.createObjectURL(blob);
      const anchor = document.createElement('a');
      anchor.download = 'sorted_' + this.getAlgorithmSelectedValue() + '.txt';
      anchor.href = url;
      anchor.click();
    }, error => {
      alert(error.message);
    });
  }

}

