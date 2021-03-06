import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import axios from 'axios';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';

import * as config from '@/shared/config/config';
import Health from '@/admin/health/health.vue';
import HealthModal from '@/admin/health/health-modal.vue';
import HealthClass from '@/admin/health/health.component';
import HealthService from '@/admin/health/health.service';

const localVue = createLocalVue();
const mockedAxios: any = axios;

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', FontAwesomeIcon);
localVue.component('health-modal', HealthModal);
localVue.directive('b-modal', {});

jest.mock('axios', () => ({
  get: jest.fn(),
}));

describe('Health Component', () => {
  let wrapper: Wrapper<HealthClass>;
  let health: HealthClass;

  beforeEach(() => {
    mockedAxios.get.mockReturnValue(Promise.resolve({}));
    wrapper = shallowMount<HealthClass>(Health, {
      store,
      i18n,
      localVue,
      stubs: {
        bModal: true,
      },
      provide: {
        healthService: () => new HealthService(),
      },
    });
    health = wrapper.vm;
  });

  it('should be a Vue instance', () => {
    expect(wrapper.isVueInstance()).toBeTruthy();
  });

  describe('baseName and subSystemName', () => {
    it('should return the basename when it has no sub system', () => {
      expect(health.baseName('base')).toBe('base');
    });

    it('should return the basename when it has sub systems', () => {
      expect(health.baseName('base.subsystem.system')).toBe('base');
    });

    it('should return the sub system name', () => {
      expect(health.subSystemName('subsystem')).toBe('');
    });

    it('should return the subsystem when it has multiple keys', () => {
      expect(health.subSystemName('subsystem.subsystem.system')).toBe(' - subsystem.system');
    });
  });

  describe('getBadgeClass', () => {
    it('should get badge class', () => {
      const upBadgeClass = health.getBadgeClass('UP');
      const downBadgeClass = health.getBadgeClass('DOWN');
      expect(upBadgeClass).toEqual('badge-success');
      expect(downBadgeClass).toEqual('badge-danger');
    });
  });

  describe('refresh', () => {
    it('should call refresh on init', async () => {
      // GIVEN
      mockedAxios.get.mockReturnValue(Promise.resolve({}));

      // WHEN
      health.refresh();
      await health.$nextTick();

      // THEN
      expect(mockedAxios.get).toHaveBeenCalledWith('management/health');
      expect(health.updatingHealth).toEqual(false);
    });
    it('should handle a 503 on refreshing health data', async () => {
      // GIVEN
      mockedAxios.get.mockReturnValue(Promise.reject({}));

      // WHEN
      health.refresh();
      await health.$nextTick();

      // THEN
      expect(mockedAxios.get).toHaveBeenCalledWith('management/health');
      expect(health.updatingHealth).toEqual(false);
    });
  });
});
