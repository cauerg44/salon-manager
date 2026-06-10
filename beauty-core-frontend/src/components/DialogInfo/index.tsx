import ButtonPrimary from '../ButtonPrimary';

type Props = {
  message: string;
  onDialogClose: Function;
}

export default function DialogModalInfo({ message, onDialogClose }: Props) {
  return (
    <div className='bcf-dialog-background' onClick={() => onDialogClose()}>

      <div className='bcf-dialog-box' onClick={(event) => event.stopPropagation()} >
        <h2>{message}</h2>

        <div onClick={() => onDialogClose()}>
          <ButtonPrimary text='Ok' />
        </div>

      </div>

    </div>
  );
}