import axios from 'axios';

const ROOT_URL = `http://localhost:8080/api`;

export const FETCH_POSTS = 'FETCH_POSTS';
export const CREATE_POST = 'CREATE_POST';
export const FETCH_POST = 'FETCH_POST';
export const DELETE_POST = 'DELETE_POST';
export const SEARCH_POSTS = 'SEARCH_POSTS';

export function fetchPosts() {
  const request = axios.get(`${ROOT_URL}/posts`);

  return {
    type: FETCH_POSTS,
    payload: request
  };
}

export function createPost(props) {
  const request = axios.post(`${ROOT_URL}/posts`, props);

  return {
    type: CREATE_POST,
    payload: request
  };
}

export function fetchPost(id) {
  const request = axios.get(`${ROOT_URL}/posts/${id}`);

  return {
    type: FETCH_POST,
    payload: request
  };
}

export function deletePost(id) {
  const request = axios.delete(`${ROOT_URL}/posts/${id}`);

  return {
    type: DELETE_POST,
    payload: request
  };
}

export function searchPosts(q) {
    const request = axios.get(`${ROOT_URL}/posts/search?q=${q}`);

    return {
        type: SEARCH_POSTS,
        payload: request
    };
}