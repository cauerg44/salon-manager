import ButtonPrimary from '../ButtonPrimary';
import ButtonSecondary from '../ButtonSecondary';

type Props = {
  id: number;
  message: string;
  onDialogAnswer: Function;
}

export default function DialogConfirmation({ id, message, onDialogAnswer }: Props) {
  return (
    <div className='bcf-dialog-background' onClick={() => onDialogAnswer(false, id)}>

      <div className='bcf-dialog-box' onClick={(event) => event.stopPropagation()} >
        <h2>{message}</h2>

        <div className='bcf-dialog-btn-container'>
          <div onClick={() => onDialogAnswer(false, id)}>
            <ButtonSecondary text='Não' />
          </div>

          <div onClick={() => onDialogAnswer(true, id)}>
            <ButtonPrimary text='Sim' />
          </div>

        </div>

      </div>
    </div>
  );
}