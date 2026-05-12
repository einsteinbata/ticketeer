import React from 'react';
import './EventCard.css';
import defaultImage from '../assets/images/concert-default.jpg';

interface EventCardProps {
    imageUrl: string;
}

const EventCard: React.FC<EventCardProps> = ({imageUrl}) => {

    return (
        <div className='event-card'>

            <div>
                {/*<img src={event1 ? event1 : defaultImage}/>*/}
                <div className='card-image-container'>
                    <img className='card-image' src={imageUrl ? imageUrl : defaultImage}/>
                </div>
            </div>

            <p>Event Name</p>
            <p>Seg • 29 Dez • 19:00</p>
            <p>Cidade</p>
            <p>Local</p>

        </div>
    )
}

export default EventCard;