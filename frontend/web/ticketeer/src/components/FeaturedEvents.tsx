import React from 'react';
import './FeaturedEvents.css';
import EventCard from "./EventCard.tsx";

const FeaturedEvents: React.FC = () => {

    const image1: string = 'https://images.unsplash.com/photo-1514525253161-7a46d19cd819?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D';
    const image2: string = 'https://images.unsplash.com/photo-1499364615650-ec38552f4f34?q=80&w=1072&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D';
    const image3: string = 'https://images.unsplash.com/photo-1763178466088-09e3678eb56b?q=80&w=1334&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D';

    return (
        <div className='featured-events'>

            <div className='event-cards-container'>
                <EventCard imageUrl={image1}/>
                <EventCard imageUrl={image2}/>
                <EventCard imageUrl={image3}/>
            </div>

        </div>
    )
}

export default FeaturedEvents;