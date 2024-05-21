<script>
    import axios from 'axios';
    import { onMount } from 'svelte';
  
    let weatherData = null;
    const apiKey = '0b8ed11771cb392de01530d985013099'; 
  
    onMount(async () => {
        try {
            const response = await axios.get('https://api.openweathermap.org/data/2.5/forecast', {
                params: {
                    lat: 47.37, // Latitude of Zurich
                    lon: 8.55, // Longitude of Zurich
                    units: 'metric', // Temperature in Celsius
                    appid: apiKey,
                },
            });
            weatherData = response.data;
        } catch (error) {
            console.error('Error fetching weather data:', error);
        }
    });
</script>
  
<style>
    .weather-card {
        border: 1px solid gray;
        margin: 1em;
        padding: 1em;
    }
    .centered-text {
        text-align: center;
    }
</style>
  
<p class="centered-text">Are you a driving instructor in the Zurich area and would like to plan driving lessons? Here you will find the current weather information for your region so that you can organize your driving lessons optimally. This weather service provides the current weather and a 5-day forecast with data every 3 hours.</p>
  
{#if weatherData}
    {#each weatherData.list as forecast}
        <div class="weather-card">
            <h2>{new Date(forecast.dt * 1000).toLocaleDateString()} {new Date(forecast.dt * 1000).toLocaleTimeString()}</h2>
            <p>Temperature: {forecast.main.temp}°C</p>
            <p>Weather: {forecast.weather[0].description}</p>
            <p>Humidity: {forecast.main.humidity}%</p>
        </div>
    {/each}
{:else}
    <p>Loading weather data...</p>
{/if}
