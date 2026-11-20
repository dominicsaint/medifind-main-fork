import { useState } from 'react'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <h1>MediFind</h1>
      <p>Pharmacy and medicine discovery, ordering, and delivery.</p>
      <div className="card">
        <button onClick={() => setCount((c) => c + 1)}>count is {count}</button>
      </div>
    </>
  )
}

export default App
