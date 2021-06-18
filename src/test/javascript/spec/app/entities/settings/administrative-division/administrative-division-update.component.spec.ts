/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import AdministrativeDivisionUpdateComponent from '@/entities/settings/administrative-division/administrative-division-update.vue';
import AdministrativeDivisionClass from '@/entities/settings/administrative-division/administrative-division-update.component';
import AdministrativeDivisionService from '@/entities/settings/administrative-division/administrative-division.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('AdministrativeDivision Management Update Component', () => {
    let wrapper: Wrapper<AdministrativeDivisionClass>;
    let comp: AdministrativeDivisionClass;
    let administrativeDivisionServiceStub: SinonStubbedInstance<AdministrativeDivisionService>;

    beforeEach(() => {
      administrativeDivisionServiceStub = sinon.createStubInstance<AdministrativeDivisionService>(AdministrativeDivisionService);

      wrapper = shallowMount<AdministrativeDivisionClass>(AdministrativeDivisionUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          administrativeDivisionService: () => administrativeDivisionServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.administrativeDivision = entity;
        // @ts-ignore
        administrativeDivisionServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(administrativeDivisionServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.administrativeDivision = entity;
        // @ts-ignore
        administrativeDivisionServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(administrativeDivisionServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
