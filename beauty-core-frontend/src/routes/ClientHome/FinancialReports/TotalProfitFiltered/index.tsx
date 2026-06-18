import './styles.css';
import { useState } from 'react';
import * as financialReportsServices from '../../../../services/financial-reports-service.ts';
import FormInput from '../../../../components/FormInput/index.tsx';
import * as forms from '../../../../utils/forms.ts';
import type { DailyProfitDTO, TotalProfitFilteredRequest } from '../../../../models/financial-report.ts';

export default function TotalProfitFiltered() {

  const [totalProfitFilteredData, setTotalProfitFilteredData] = useState<TotalProfitFilteredRequest>();

  const [dailyProfit, setDailyProfit] = useState<DailyProfitDTO[]>();
  const [totalCalculated, setTotalCalculated] = useState<number>();

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
        setDailyProfit(response.data.list);
        setTotalCalculated(response.data.totalCalculated);
      })
      .catch(() => {
        console.log("Deu erro na requisição");
      });
  }

  return (
    <section id="total-profit-filtered-section" className="bcf-container-1200px">

      <h2 className='bcf-form-title-section'>
        Consulte total por período
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
        dailyProfit && totalCalculated &&
        <>
          <h4><strong>Relatório</strong> do total filtrado entre {totalProfitFilteredData?.start} e {totalProfitFilteredData?.end}:</h4><br />
          {
            dailyProfit.map(resultSet => (
              <div key={resultSet.date} className='bcf-result-set-info'>
                <h4>{resultSet.date}: </h4> <h5>R$ {resultSet.total.toFixed(2)}</h5>
              </div>
            ))
          }
          <h4><strong>Total: </strong> <span>R$ {totalCalculated.toFixed(2)}</span></h4>
        </>
      }
    </section>
  );
}