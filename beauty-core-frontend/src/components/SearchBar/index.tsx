import { useState } from 'react';
import './styles.css';

type Props = {
  onSearch: Function;
}

export default function SearchBar({ onSearch }: Props) {

  const [text, setText] = useState<string>("");

  function handleInputChange(event: any) {
    setText(event.target.value);
  }

  function handleSubmit(event: any) {
    event.preventDefault();
    onSearch(text);
  }

  function handleResetClick() {
    setText("");
    onSearch(text);
  }

  return (
    <div className='bcf-search-bar'>
      <form onSubmit={handleSubmit}>
        <button type='submit' className='btn-left'>🔎︎</button>
        <input
          value={text}
          placeholder='Digite o nome do cliente'
          type="text"
          onChange={handleInputChange}
        />
        <button onClick={handleResetClick} className='btn-right'>🗙</button>
      </form>
    </div>
  );
}