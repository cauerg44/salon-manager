import './styles.css';
import { useEffect, useState } from 'react';
import * as financialReportsServices from '../../../../services/financial-reports-service.ts';
import FormInput from '../../../../components/FormInput/index.tsx';
import * as forms from '../../../../utils/forms.ts';
import type { TotalProfitFilteredRequest } from '../../../../models/financial-report.ts';
import type { ProfessionalLoggedDTO } from '../../../../models/professional-logged.ts';
import * as professionalService from '../../../../services/professional-service.ts';

export default function TotalProfitFiltered() {

  const [totalProfitFiltered, setTotalProfitFiltered] = useState<number>();

  const [totalProfitFilteredData, setTotalProfitFilteredData] = useState<TotalProfitFilteredRequest>();

  const [professionalLogged, setProfessionalLogged] = useState<ProfessionalLoggedDTO>();

  const [formData, setFormData] = useState<any>({
    start: {
      value: "",
      id: "start",
      name: "start",
      type: "text",
      placeholder: "Início (dia/mês/ano)",
      mask: "dd/mm/yyyy",
      replacement: {
        d: /\d/,
        m: /\d/,
        y: /\d/,
      },
      validation: function (value: string = "") {
        return /^(0[1-9]|[12][0-9]|3[01])\/(0[1-9]|1[0-2])\/\d{4}$/.test(value);
      },
      message: "Favor informar uma data válida",
    },
    end: {
      value: "",
      id: "end",
      name: "end",
      type: "text",
      placeholder: "Final (dia/mês/ano)",
      mask: "dd/mm/yyyy",
      replacement: {
        d: /\d/,
        m: /\d/,
        y: /\d/,
      },
      validation: function (value: string = "") {
        return /^(0[1-9]|[12][0-9]|3[01])\/(0[1-9]|1[0-2])\/\d{4}$/.test(value);
      },
      message: "Favor informar uma data válida",
    }
  });

  useEffect(() => {
    professionalService.findProfessionalLogged()
      .then(response => {
        setProfessionalLogged(response.data);
      })
  }, []);

  function handleTurnDirty(name: string) {
    setFormData(forms.dirtyAndValidate(formData, name));
  }

  function handleInputChange(event: any) {
    setFormData(forms.updateAndValidate(formData, event.target.name, event.target.value));
  }

  function handleSubmit(event: any) {
    event.preventDefault();

    const formDataValidated = forms.dirtyAndValidateAll(formData);

    if (forms.hasAnyInvalid(formDataValidated)) {
      setFormData(formDataValidated);
      return;
    }

    const requestBody = forms.toValues(formDataValidated);
    setTotalProfitFilteredData(requestBody);

    financialReportsServices.getTotalProfitFiltered(requestBody)
      .then(response => {
        setTotalProfitFiltered(response.data);
      })
      .catch(() => {
        console.log("Deu erro");
      })
  }

  return (
    <section id="total-profit-filtered-section" className="bcf-container-1200px">

      <h2 className='bcf-form-title-section'>
        Filtre seu total apurado
      </h2>

      <div className='bcf-form-modal-container'>
        <h3>Filtros: </h3>

        <form onSubmit={handleSubmit} className='bcf-form-modal'>

          <div className="bcf-form-control">
            <FormInput
              {...formData.start}
              onTurnDirty={handleTurnDirty}
              onChange={handleInputChange}
            />
            <div className='bcf-form-error'>{formData.start.message}</div>
          </div>

          <div className="bcf-form-control">
            <FormInput
              {...formData.end}
              onTurnDirty={handleTurnDirty}
              onChange={handleInputChange}
            />
            <div className='bcf-form-error'>{formData.end.message}</div>
          </div>

          <button type='submit'>
            Filtrar
          </button>

        </form>
      </div>

      {
        totalProfitFiltered &&
        <h4><strong>{professionalLogged?.name}</strong>, seu total filtrado entre {totalProfitFilteredData?.start} e {totalProfitFilteredData?.end}: <br /><span>R$ {totalProfitFiltered.toFixed(2)}</span></h4>
      }
    </section>
  );
}