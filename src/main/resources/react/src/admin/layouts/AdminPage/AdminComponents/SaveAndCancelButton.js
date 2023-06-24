export const SaveAndCancelButton = ({ closeModal, clearModel }) => {
  return (
    <div className="text-right mt-4">
      <button type="submit" className="btn btn-primary me-2">
        Save
      </button>
      <button
        type="button"
        className="btn btn-secondary me-2"
        onClick={clearModel}
      >
        Clear
      </button>
      <button type="button" className="btn btn-secondary " onClick={closeModal}>
        Cancel
      </button>
    </div>
  );
};
