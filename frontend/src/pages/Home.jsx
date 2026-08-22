import Navbar from '../components/Navbar'
import Hero from '../components/Hero'
import SearchBar from '../components/SearchBar'
import FeaturedBooks from '../components/FeaturedBooks'
import HowItWorks from '../components/HowItWorks'

function Home() {
  return (
    <div>
      <Navbar />
      <Hero />
      <SearchBar />
      <FeaturedBooks />
      <HowItWorks />
    </div>
  )
}

export default Home