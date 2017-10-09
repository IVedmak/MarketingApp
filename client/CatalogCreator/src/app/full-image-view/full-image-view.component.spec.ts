import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FullImageViewComponent } from './full-image-view.component';

describe('FullImageViewComponent', () => {
  let component: FullImageViewComponent;
  let fixture: ComponentFixture<FullImageViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FullImageViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FullImageViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
