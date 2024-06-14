'use client'

import { ChangeEvent, FormEvent, useState } from 'react';


export default function Home() {
  const WORD = 'ГРОЗА'

  const [input, setInput] = useState<string>('')
  const [guesses, setGuesses] = useState<string[]>([]);
  const [message, setMessage] = useState('');
  const [isVictory, setIsVictory] = useState<boolean>(false);

  const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (isVictory) {
      return;
    }
    if (input.length !== 5) {
      setMessage('Должно быть ровно 5 букв');
      return;
    }
    if (guesses.length >= 5) {
      setMessage('Вы потратили все попытки :(');
      return;
    }
    if (input === WORD) {
      setMessage(`Победа! использовано попыток: ${guesses.length + 1}`)
      setGuesses([...guesses, input]);
      setIsVictory(true);
      return
    }
    setGuesses([...guesses, input]);
    setInput('');
    setMessage(message);
  };

  const handleInputChange = (e: ChangeEvent<HTMLInputElement>) => {
    setInput(e.target.value.toUpperCase());
  };

  const renderCell = (letter: string, index: number) => {
    if (!WORD.includes(letter)) return 'bg-gray-500';
    if (WORD[index] === letter) return 'bg-green-500';
    return 'bg-yellow-500';
  };

  return (
    <main className='w-screen h-screen flex justify-center items-center'>
      <div className='w-80 flex flex-col gap-4'>
        <h1 className='w-full text-2xl text-center'>Вовсе не Wordle</h1>
        <div className='w-full h-80 grid grid-cols-5 grid-rows-5 gap-2'>
          {guesses.map((guess) => (
            guess.split('').map((letter, j) => (
                  <div
                    key={j}
                    className={`text-white font-bold flex items-center justify-center border ${renderCell(letter, j)}`}
                  >
                    {letter}
                  </div>
                ))
            ))}
        </div>
        <form className='w-full flex flex-col gap-2' onSubmit={handleSubmit}>
          <input
            className='border border-blue-500 text-xl rounded-md p-2 text-center'
            readOnly={isVictory}
            type="text"
            maxLength={5}
            minLength={5}
            value={input}
            onChange={handleInputChange}
          />
          <input className='bg-blue-500 text-white text-xl rounded-md p-2' type="submit" value="Ввести"/>
        </form>
        <p className='w-full text-red-600 text-center'>{message}</p>
      </div>
    </main>
  );
}
