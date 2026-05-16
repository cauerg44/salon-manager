import './styles.css'

type Props = {
  text: string;
}

export default function ButtonPrimary({ text }: Props) {
  return (
    <button className='bcf-btn-primary'>{text}</button>
  );
}