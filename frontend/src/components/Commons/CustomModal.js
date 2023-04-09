import React from 'react';
import {Modal} from 'native-base';

const CustomModal = ({showModal, setShowModal, heading, body}) => (
  <Modal
    isOpen={showModal}
    onClose={() => setShowModal(false)}
    _backdrop={{
      bg: 'black',
    }}>
    <Modal.Content maxWidth="400" maxH="400" backgroundColor="tertiary.50">
      <Modal.Header backgroundColor="secondary.300">{heading}</Modal.Header>
      <Modal.Body>{body}</Modal.Body>
    </Modal.Content>
  </Modal>
);

export default CustomModal;
