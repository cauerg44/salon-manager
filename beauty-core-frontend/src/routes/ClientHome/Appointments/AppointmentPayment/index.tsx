import './styles.css';
import { useParams } from 'react-router-dom';
import { useState } from 'react';
import * as forms from '../../../../utils/forms.ts';
import FormSelect from '../../../../components/FormSelect/index.tsx';
import { selectStyles } from '../../../../utils/select.ts';
import FormInput from '../../../../components/FormInput/index.tsx';
import type { PaymentDTO } from '../../../../models/payment.ts';
import * as paymentService from '../../../../services/payment-service.ts';

export default function AppointmentPayment() {

  const params = useParams();

  const [isPaid, setIsPaid] = useState<boolean>(false);

  const [formData, setFormData] = useState<any>({
    paymentMethod: {
      value: null,
      id: 'paymentMethod',
      name: 'paymentMethod',
      placeholder: 'Forma de pagamento',
      validation: function (value: PaymentDTO) {
        return value != null;
      },
      message: "Favor escolher o método de pagamento"
    },
    amount: {
      value: 0,
      id: "amount",
      name: "amount",
      type: "number",
      placeholder: "Valor",
      validation: function (value: any) {
        return Number(value) > 0;
      },
      message: "O valor deve ser positivo",
    }
  })

  const paymentMethods = [
    { value: 'PIX', label: 'PIX' },
    { value: 'Dinheiro', label: 'CASH' },
    { value: 'Débito', label: 'DEBIT' },
    { value: 'Crédito', label: 'CREDIT' }
  ];

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

    const requestBody = forms.toValues(formData);

    const requestBodyAdapted = {
      paymentMethod: requestBody.paymentMethod.label,
      amount: requestBody.amount
    }

    paymentService.createPayment(Number(params.appointmentId), requestBodyAdapted)
      .then(() => {
        setIsPaid(true);
      })
      .catch(() => {
        setIsPaid(false);
      })
  }

  return (
    <>
      <section id='professional-form-section' className='bcf-container-1200px'>

        <h2 className='bcf-form-title-section'>
          Registrar pagamento para atendimento de número: {Number(params.appointmentId)}
        </h2>

        <div className='bcf-form-modal-container'>
          <h3>Dados do pagamento: </h3>

          <form onSubmit={handleSubmit} className='bcf-form-modal'>

            <FormSelect
              {...formData.paymentMethod}
              className='bcf-form-select bcf-form-select-container'
              styles={selectStyles}
              options={paymentMethods}
              onChange={(obj: any) => {
                const newFormData = forms.updateAndValidate(formData, "paymentMethod", obj);
                setFormData(newFormData);
              }}
              onTurnDirty={handleTurnDirty}
              getOptionLabel={(obj: any) => obj.value}
            />
            <div className='bcf-form-error'>{formData.paymentMethod.message}</div>

            <div className="bcf-form-control">
              <FormInput
                {...formData.amount}
                onTurnDirty={handleTurnDirty}
                onChange={handleInputChange}
              />
              <div className='bcf-form-error'>{formData.amount.message}</div>
            </div>

            <button type='submit'>
              Registrar pagamento
            </button>

          </form>
        </div>
        {
          isPaid &&
          <h3 className='bcf-payment-message'>Pagamento feito com sucesso!</h3>
        }
      </section>
    </>
  );
}