import { TestBed } from '@angular/core/testing';

import { NewsDetailService } from '../src/app/services/news-detail.service';

describe('NewsDetailService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created News details service', () => {
    const service: NewsDetailService = TestBed.get(NewsDetailService);
    expect(service).toBeTruthy();
  });
});
