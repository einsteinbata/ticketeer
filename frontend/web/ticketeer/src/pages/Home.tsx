import React from 'react';
import './Home.css';
import SearchComponent from "../components/SearchComponent.tsx";
import FeaturedEvents from "../components/FeaturedEvents.tsx";

const Home: React.FC = () => {
    return (
        <div>
            <p className='hero-text'>As Melhores <span className='highlighted-hero-text'>Experiências</span>, <br/> Ao Seu Alcance!</p>

            <div className='search-component-container home-section'>
                <SearchComponent/>
            </div>

            <div className='featured-events-container home-section'>
                <FeaturedEvents/>
            </div>

        </div>
    );
}

export default Home;